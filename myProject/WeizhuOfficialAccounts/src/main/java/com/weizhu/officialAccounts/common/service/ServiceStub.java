package com.weizhu.officialAccounts.common.service;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.googlecode.protobuf.format.JsonFormat;
import com.weizhu.officialAccounts.common.proto.ResponseType;
import com.weizhu.officialAccounts.common.service.exception.HeadUnknownException;
import com.weizhu.officialAccounts.common.service.exception.InvokeUnknownException;
import com.weizhu.officialAccounts.common.service.exception.RequestParseException;
import com.weizhu.officialAccounts.common.service.exception.ResponseParseException;

public final class ServiceStub {
	
	private ServiceStub() {
	}

	@SuppressWarnings("unchecked")
	public static <T> T createServiceApi(Class<T> serviceApi, T serviceImpl, Executor serviceExecutor) {
		return (T) Proxy.newProxyInstance(serviceApi.getClassLoader(), new Class<?>[]{serviceApi}, 
				new ServiceImplApiHandler(serviceApi, serviceImpl, serviceExecutor));
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T createServiceApi(Class<T> serviceApi, ServiceInvoker serviceInvoker) {
		return (T) Proxy.newProxyInstance(serviceApi.getClassLoader(), new Class<?>[]{serviceApi}, 
				new ServiceInvokerApiHandler(serviceApi, serviceInvoker));
	}
	
	public static <T> ServiceInvoker createServiceInvoker(Class<T> serviceApi, T serviceImpl, Executor serviceExecutor) {
		return new LocalServiceInvoker(serviceApi, serviceImpl, serviceExecutor);
	}
	
	private static final class ServiceImplApiHandler implements InvocationHandler {

		private final String serviceName;
		private final ImmutableMap<String, ImmutableMap<Class<?>, Func>> funcMap;
		private final Object serviceImpl;
		private final Executor serviceExecutor;
		
		ServiceImplApiHandler(Class<?> serviceApi, Object serviceImpl, Executor serviceExecutor) {
			Map<String, Map<Class<?>, Func>> tmpFuncMap = new HashMap<String, Map<Class<?>, Func>>();
			for (Method method : serviceApi.getMethods()) {
				checkMethod(serviceApi, method);
				
				final String functionName = method.getName();
				final Class<?> headType = method.getParameterTypes()[0];
				final boolean isSyncImpl;
				try {
					isSyncImpl = serviceImpl.getClass().getMethod(method.getName(), method.getParameterTypes()).getAnnotation(SyncImpl.class) != null;
				} catch (Throwable th) {
					throw new Error("invalid impl func : " + method.getName(), th);
				}
				
				Map<Class<?>, Func> headMap = tmpFuncMap.get(functionName);
				if (headMap == null) {
					headMap = new HashMap<Class<?>, Func>();
					tmpFuncMap.put(functionName, headMap);
				}
				headMap.put(headType, new Func(isSyncImpl));
			}
			
			ImmutableMap.Builder<String, ImmutableMap<Class<?>, Func>> builder = ImmutableMap.builder();
			for (Map.Entry<String, Map<Class<?>, Func>> entry : tmpFuncMap.entrySet()) {
				builder.put(entry.getKey(), ImmutableMap.copyOf(entry.getValue()));
			}
			
			this.serviceName = serviceApi.getSimpleName();
			this.funcMap = builder.build();
			this.serviceImpl = serviceImpl;
			this.serviceExecutor = serviceExecutor;
		}
		
		public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {
			final long beginTime = System.currentTimeMillis();
			
			ListenableFuture<Message> future = null;
			try {
				future = invoke0(proxy, method, args);
			} catch (Throwable th) {
				future = Futures.immediateFailedFuture(th);
			}
		
			Futures.addCallback(future, 
					new ServiceApiLogCallback(serviceName, method.getName(), 
							args.length > 0 && args[0] instanceof Message ? (Message)args[0] : null,
							args.length > 1 && args[1] instanceof Message ? (Message)args[1] : null,
							beginTime
					));
			return future;
		}
		
		@SuppressWarnings("unchecked")
		private ListenableFuture<Message> invoke0(Object proxy, final Method method, final Object[] args) throws Throwable {
			final ImmutableMap<Class<?>, Func> headMap = this.funcMap.get(method.getName());
			if (headMap == null) {
				return Futures.immediateFailedFuture(new InvokeUnknownException("func: " + method.getName()));
			}
			
			final Class<?> headClazz = method.getParameterTypes().length > 0 ? method.getParameterTypes()[0] : null;
			if (headClazz == null) {
				return Futures.immediateFailedFuture(new HeadUnknownException("head is null, func: " + method.getName()));
			}

			final Func func = headMap.get(headClazz);
			if (func == null) {
				return Futures.immediateFailedFuture(new HeadUnknownException("func: " + method.getName() + ", head: " + headClazz));
			}
			
			if (func.isSyncImpl && !(Thread.currentThread() instanceof ServiceThread)) {
				final SettableFuture<Message> settableFuture = SettableFuture.create();
				this.serviceExecutor.execute(new Runnable() {

					public void run() {
						ListenableFuture<Message> future = null;
						try {
							future = (ListenableFuture<Message>) method.invoke(serviceImpl, args);
						} catch (InvocationTargetException e) {
							settableFuture.setException(e.getTargetException());
							return;
						} catch (Throwable th) {
							settableFuture.setException(th);
							return;
						}
						
						try {
							settableFuture.set(future.get());
						} catch (ExecutionException e) {
							settableFuture.setException(e.getCause());
						} catch (Throwable th) {
							settableFuture.setException(th);
						}
					}
					
				});
				return settableFuture;
			} else {
				try {
					return (ListenableFuture<Message>) method.invoke(serviceImpl, args);
				} catch (InvocationTargetException e) {
					return Futures.immediateFailedFuture(e.getTargetException());
				} catch (Throwable th) {
					return Futures.immediateFailedFuture(th);
				}
			}
		}
		
		private static final class Func {
			final boolean isSyncImpl;
			Func(boolean isSyncImpl) {
				this.isSyncImpl = isSyncImpl;
			}
		}
		
	}
	
	private static final class ServiceInvokerApiHandler implements InvocationHandler {
		
		private final ImmutableMap<String, ImmutableMap<Class<?>, Parser<? extends Message>>> responseParserMap;
		private final ServiceInvoker serviceInvoker;
		
		ServiceInvokerApiHandler(Class<?> serviceApi, ServiceInvoker serviceInvoker) {
			
			Map<String, Map<Class<?>, Parser<? extends Message>>> tmpResponseParserMap = new HashMap<String, Map<Class<?>, Parser<? extends Message>>>();
			for (Method method : serviceApi.getMethods()) {
				final String functionName = method.getName();
				final Class<?> returnType = method.getReturnType();
				final Class<?>[] paramTypes = method.getParameterTypes();
				final ResponseType responseType = method.getAnnotation(ResponseType.class);
				
				if (returnType == ListenableFuture.class 
						&& paramTypes.length == 2 
						&& Message.class.isAssignableFrom(paramTypes[0])
						&& Message.class.isAssignableFrom(paramTypes[1])
						&& responseType != null
						&& Message.class.isAssignableFrom(responseType.value())) {
					
					Map<Class<?>, Parser<? extends Message>> headParserMap = tmpResponseParserMap.get(functionName);
					if (headParserMap == null) {
						headParserMap = new HashMap<Class<?>, Parser<? extends Message>>();
						tmpResponseParserMap.put(functionName, headParserMap);
					}
					headParserMap.put(paramTypes[0], fetchParser(responseType.value()));
				}
			}
			
			ImmutableMap.Builder<String, ImmutableMap<Class<?>, Parser<? extends Message>>> builder = ImmutableMap.builder();
			for (Map.Entry<String, Map<Class<?>, Parser<? extends Message>>> entry : tmpResponseParserMap.entrySet()) {
				builder.put(entry.getKey(), ImmutableMap.copyOf(entry.getValue()));
			}
			
			this.responseParserMap = builder.build();
			this.serviceInvoker = serviceInvoker;
		}
		
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			final String functionName = method.getName();
			final Map<Class<?>, Parser<? extends Message>> headParserMap = responseParserMap.get(functionName);
			
			if (headParserMap == null) {
				throw new InvokeUnknownException("func: " + functionName);
			}
			
			final Class<?> headType = method.getParameterTypes().length > 0 ? method.getParameterTypes()[0] : null;
			final Parser<? extends Message> responseParser = headType != null ? headParserMap.get(headType) : null;
			
			if (responseParser == null) {
				throw new HeadUnknownException("func: " + functionName + ", head: " + headType);
			}
			
			ListenableFuture<ByteString> future = serviceInvoker.invoke(functionName, (Message) args[0], ((Message) (args[1])).toByteString());
			return Futures.transform(future, new AsyncFunction<ByteString, Message>() {

				public ListenableFuture<Message> apply(ByteString responseBody) throws Exception {
					try {
						return Futures.immediateFuture((Message) responseParser.parseFrom(responseBody));
					} catch(InvalidProtocolBufferException e) {
						return Futures.immediateFailedFuture(new ResponseParseException(e));
					}
				}
				
			});
		}
		
	}
	
	private static final class LocalServiceInvoker implements ServiceInvoker {
		
		private final String serviceName;
		private final ImmutableMap<String, ImmutableMap<Class<?>, Func>> funcMap;
		private final Object serviceImpl;
		private final Executor serviceExecutor;
		
		LocalServiceInvoker(Class<?> serviceApi, Object serviceImpl, Executor serviceExecutor) {
			
			this.serviceName = serviceApi.getSimpleName();
			
			Map<String, Map<Class<?>, Func>> tmpFuncMap = new HashMap<String, Map<Class<?>, Func>>();
			for (Method method : serviceApi.getMethods()) {
				checkMethod(serviceApi, method);

				final String functionName = method.getName();
				final Class<?> headType = method.getParameterTypes()[0];
				final Class<?> requestType = method.getParameterTypes()[1];
				final boolean isSyncImpl;
				try {
					isSyncImpl = serviceImpl.getClass().getMethod(method.getName(), method.getParameterTypes()).getAnnotation(SyncImpl.class) != null;
				} catch (Throwable th) {
					throw new Error("invalid impl func : " + method.getName(), th);
				}
				
				Map<Class<?>, Func> headMap = tmpFuncMap.get(functionName);
				if (headMap == null) {
					headMap = new HashMap<Class<?>, Func>();
					tmpFuncMap.put(functionName, headMap);
				}
				headMap.put(headType, new Func(fetchParser(requestType), method, isSyncImpl));
			}
			
			ImmutableMap.Builder<String, ImmutableMap<Class<?>, Func>> builder = ImmutableMap.builder();
			for (Map.Entry<String, Map<Class<?>, Func>> entry : tmpFuncMap.entrySet()) {
				builder.put(entry.getKey(), ImmutableMap.copyOf(entry.getValue()));
			}
			
			this.funcMap = builder.build();
			this.serviceImpl = serviceImpl;
			this.serviceExecutor = serviceExecutor;
		}
		
		public String serviceName() {
			return serviceName;
		}
		
		@SuppressWarnings("unchecked")
		public ListenableFuture<ByteString> invoke(String functionName, final Message head, ByteString requestBody) {
			
			final long beginTime = System.currentTimeMillis();
			
			final ImmutableMap<Class<?>, Func> headMap = this.funcMap.get(functionName);
			if (headMap == null) {
				return Futures.immediateFailedFuture(new InvokeUnknownException("func: " + functionName));
			}
			
			final Func func = headMap.get(head.getClass());
			if (func == null) {
				return Futures.immediateFailedFuture(new HeadUnknownException("func: " + functionName + ", head: " + head.getClass()));
			}
			
			final Message request;
			try {
				request = func.requestParser.parseFrom(requestBody);
			} catch (InvalidProtocolBufferException e) {
				return Futures.immediateFailedFuture(new RequestParseException(e));
			}
			
			if (func.isSyncImpl && !(Thread.currentThread() instanceof ServiceThread)) {
				final SettableFuture<ByteString> settableFuture = SettableFuture.create();
				this.serviceExecutor.execute(new Runnable() {

					public void run() {
						ListenableFuture<Message> future = null;
						try {
							future = (ListenableFuture<Message>) func.method.invoke(serviceImpl, head, request);
						} catch (InvocationTargetException e) {
							future = Futures.immediateFailedFuture(e.getTargetException());
						} catch (Throwable th) {
							future = Futures.immediateFailedFuture(th);
						}
						
						try {
							settableFuture.set(future.get().toByteString());
						} catch (ExecutionException e) {
							settableFuture.setException(e.getCause());
						} catch (Throwable th) {
							settableFuture.setException(th);
						}
						
						Futures.addCallback(future, new ServiceApiLogCallback(serviceName, func.method.getName(), head, request, beginTime));
						
					}
					
				});
				return settableFuture;
			} else {
				ListenableFuture<Message> repsonseFuture;
				try {
					repsonseFuture = (ListenableFuture<Message>) func.method.invoke(serviceImpl, head, request);
				} catch (InvocationTargetException e) {
					repsonseFuture = Futures.immediateFailedFuture(e.getTargetException());
				} catch (Throwable th) {
					repsonseFuture = Futures.immediateFailedFuture(th);
				}
				
				Futures.addCallback(repsonseFuture, new ServiceApiLogCallback(serviceName, func.method.getName(), head, request, beginTime));
				
				return Futures.transform(repsonseFuture, TRANSFORM_FUNCTION);
			}
		}
		
		private static final Function<Message, ByteString> TRANSFORM_FUNCTION = new Function<Message, ByteString>() {

			public ByteString apply(Message response) {
				return response.toByteString();
			}
			
		};
		
		private static final class Func {
			final Parser<? extends Message> requestParser;
			final Method method;
			final boolean isSyncImpl;
			
			Func(Parser<? extends Message> requestParser, Method method, boolean isSyncImpl) {
				this.requestParser = requestParser;
				this.method = method;
				this.isSyncImpl = isSyncImpl;
			}
		}
		
	}
	
	
	private static void checkMethod(Class<?> serviceApi, Method method) {
		final Class<?> returnType = method.getReturnType();
		final Class<?>[] paramTypes = method.getParameterTypes();
		
		if (returnType != ListenableFuture.class 
				//|| paramTypes.length != 2 修改后参数变为一个
				|| paramTypes.length != 1 
				|| !Message.class.isAssignableFrom(paramTypes[0])
				//|| !Message.class.isAssignableFrom(paramTypes[1])
				) {
			throw new Error("invalid method : " + serviceApi.getSimpleName() + "." + method.getName());
		}
	}
	
	private static Parser<? extends Message> fetchParser(Class<?> type) {
		try {
			return ((Message) (type.getMethod("getDefaultInstance").invoke(null))).getParserForType();
		} catch (Exception e) {
			throw new IllegalArgumentException("type cannot fetch parser : " + type, e);
		}
	}
		
	private static final Logger SERVICE_API_READ_LOGGER = LoggerFactory.getLogger("weizhu_service_api_read");
	private static final Logger SERVICE_API_WRITE_LOGGER = LoggerFactory.getLogger("weizhu_service_api_write");
	
	private static final Gson GSON = new Gson();
	
	private static final class ServiceApiLogCallback implements FutureCallback<Message> {

		private final String serviceName;
		private final String functionName;
		private final Message head;
		private final Message request;
		private final long beginTime;
		
		ServiceApiLogCallback(String serviceName, String functionName, Message head, Message request, long beginTime) {
			this.serviceName = serviceName;
			this.functionName = functionName;
			this.head = head;
			this.request = request;
			this.beginTime = beginTime;
		}
		
		public void onSuccess(Message response) {
			final boolean isReadMethod = isReadMethod(this.serviceName, this.functionName);
			
			if ((isReadMethod && SERVICE_API_READ_LOGGER.isInfoEnabled())
					|| (!isReadMethod && SERVICE_API_WRITE_LOGGER.isInfoEnabled())
					) {
				
				StringBuilder json = new StringBuilder();
				try {
					json.append("{\"service\":\"").append(this.serviceName);
					json.append("\",\"function\":\"").append(this.functionName);
					json.append("\",\"duration\":").append(System.currentTimeMillis() - this.beginTime);
					if (head != null) {
						json.append(",\"head_class\":\"").append(head.getClass().getSimpleName());
						json.append("\",\"head\":");
						JsonFormat.print(head, json);
					}
					if (request != null) {
						json.append(",\"request_class\":\"").append(request.getClass().getSimpleName());
						json.append("\",\"request\":");
						JsonFormat.print(request, json);
					}
					if (response != null) {
						json.append(",\"response_class\":\"").append(response.getClass().getSimpleName());
						json.append("\",\"response\":");
						JsonFormat.print(response, json);
					}
					json.append("}");
					
					if (isReadMethod) {
						SERVICE_API_READ_LOGGER.info(json.toString());
					} else {
						SERVICE_API_WRITE_LOGGER.info(json.toString());
					}
				} catch (IOException e) {
					if (isReadMethod) {
						SERVICE_API_READ_LOGGER.error("print fail! json fragment : " + json.toString(), e);
					} else {
						SERVICE_API_WRITE_LOGGER.error("print fail! json fragment : " + json.toString(), e);
					}
				}
			}
		}

		public void onFailure(Throwable t) {
			final boolean isReadMethod = isReadMethod(this.serviceName, this.functionName);
			
			if ((isReadMethod && SERVICE_API_READ_LOGGER.isErrorEnabled())
					|| (!isReadMethod && SERVICE_API_WRITE_LOGGER.isErrorEnabled())
					) {
				
				StringBuilder json = new StringBuilder();
				try {
					json.append("{\"service\":\"").append(this.serviceName);
					json.append("\",\"function\":\"").append(this.functionName);
					json.append("\",\"duration\":").append(System.currentTimeMillis() - this.beginTime);
					if (head != null) {
						json.append(",\"head_class\":\"").append(head.getClass().getSimpleName());
						json.append("\",\"head\":");
						JsonFormat.print(head, json);
					}
					if (request != null) {
						json.append(",\"request_class\":\"").append(request.getClass().getSimpleName());
						json.append("\",\"request\":");
						JsonFormat.print(request, json);
					}
					if (t != null) {
						json.append(",\"exception_class\":\"").append(t.getClass().getSimpleName());
						json.append("\",\"exception\":");
						
						JsonObject exceptionObj = new JsonObject();
						exceptionObj.addProperty("message", t.getMessage());
						exceptionObj.addProperty("stacktrace", Throwables.getStackTraceAsString(t));
						GSON.toJson(exceptionObj, json);
					}
					json.append("}");
					
					if (isReadMethod) {
						SERVICE_API_READ_LOGGER.error(json.toString());
					} else {
						SERVICE_API_WRITE_LOGGER.error(json.toString());
					}
				} catch (IOException e) {
					if (isReadMethod) {
						SERVICE_API_READ_LOGGER.error("print fail! json fragment : " + json.toString(), e);
					} else {
						SERVICE_API_WRITE_LOGGER.error("print fail! json fragment : " + json.toString(), e);
					}
				}
			}
		}
		
		private static boolean isReadMethod(String serviceName, String functionName) {
			if (functionName.startsWith("get") || functionName.startsWith("Get") 
					|| functionName.startsWith("search") || functionName.startsWith("Search")
					) {
				return true;
			}
			
			if ("SessionService".equals(serviceName) && "verifySessionKey".equals(functionName)) {
				return true;
			}
			
			if ("StatsService".equals(serviceName) && functionName.startsWith("report")) {
				return true;
			}
			
			if ("SystemService".equals(serviceName) && functionName.equals("checkNewVersion")) {
				return true;
			}
			
			if ("AllowService".equals(serviceName) && functionName.equals("checkAllow")) {
				return true;
			}
			
			if ("AdminService".equals(serviceName) && functionName.equals("adminVerifySession")) {
				return true;
			}
			
			return false;
		}
		
	}
}
