package com.lscdz.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import yangjian.frame.sso.UserData;
import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;
import yangjian.frame.util.ValConstant;
import yangjian.frame.vo.qxgl_client;

/**
 * 客户端交互处理 Servlet 类，用于接收客户端的请求 处理客户端除登录外所有的报文请求
 * 
 * @author jasper 2013/03/08
 */
public class ClientActionServlet extends HttpServlet {
	private static final long serialVersionUID = 20130701001L;
	public static final String CONTENT_TYPE_XML = "application/x-www-form-urlencoded";
	public static final String CONTENT_TYPE_UPLOAD_FILE = "multipart/form-data";
	public static final String CONTENT_TYPE_DOWNLOAD_FILE = "application/download-file";
	public static final String CONTENT_TYPE_APPLICATION_STREAM = "application/octet-stream";

	// 临时文件目录，将在初始化的时候从配置文件中读取
	private static String _pathTempFile = null;
	public static String getTempFilePath() {
		return _pathTempFile;
	}
	
	// debug 模式
	private static boolean _isDebug = true;
	public static boolean isDebug() {
		return _isDebug;
	}

	// 是否启用操作权限检查
	private static boolean _checkPrivilege = false;
	
	/**
	 * 服务启动时候运行此方法,初始化Message数据
	 */
	public void init(ServletConfig config) throws ServletException {
		// 读取配置文件中的根目录
		_pathTempFile = ResourceManager.getTokenByName("PATH_TEMP_FILE");
		if (_pathTempFile.equals("")) {
			// 未配置，使用当前用户的目录
			_pathTempFile = System.getProperty("user.home");
		}
		
		// 读取配置文件中的 debug 模式
		_isDebug = (ResourceManager.getTokenByName("DEBUG_MODE")
				.equalsIgnoreCase("false") ? false : true);
		
		// 读取配置文件中的操作权限检查标识
		_checkPrivilege = (ResourceManager.getTokenByName("CHECK_PRIVILEGE")
				.equalsIgnoreCase("false") ? false : true);
		
		// 初始化 Servlet 日志文件
		UtilLogger.initLogWriter(_pathTempFile + System.getProperty("file.separator") + "ClientActionServlet.log");
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 处理客户端请求，首先直接对报文进行解析，如果成功解析 读取报文中的 RequestAction，创建 ClientMessage 对象，赋值
	 * type 和 action 然后将 Document 对象赋值，根据 type 和 action 调用不同的 Handler 进行后续处理。
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 针对没有登录 web 的情况，生成一个 UserData 实例，放入 Session，以便后续程序使用其中的 userMap
        HttpSession session = request.getSession(true);
        Object userData = session.getAttribute(ValConstant.SESSION_USERDATA);
        if (userData == null) {
        	session.setAttribute(ValConstant.SESSION_USERDATA, new UserData());
        }		
		
		// 根据传入的 ContentType 进行不同的处理
		String contentType = request.getContentType();
		if (CONTENT_TYPE_XML.equals(contentType)) {
			// 未加密 XML 报文
			doClientXml(request, response);
		} else if (contentType.startsWith(CONTENT_TYPE_UPLOAD_FILE)) {
			// 文件上传
			doDataSubmitClientFileUpload(request, response);
		} else if (contentType.startsWith(CONTENT_TYPE_DOWNLOAD_FILE)) {
			// 文件下载
			doClientFileDownload(request, response);
		} else {
			// 不支持的内容格式
			PrintWriter out = response.getWriter();
			out.write("Content Type: " + contentType + " is not supported!");
			out.flush();
			out.close();
		}
	}

	/**
	 * 处理展示客户端下载文件请求
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void doClientFileDownload(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		/*
		// 获取请求文件 action
		String action = request.getParameter("Action");
		String errMsg = null;
		if (action != null && action.length() > 0) {
			// 获取请求文件的文件名
			String filename = dataSubmitFileMap.get(action);
			if (filename != null && (new File(filename)).exists()) {
				// 返回文件内容
				writeFileData(response, filename);
			} else {
				errMsg = "No such file! " + filename;
			}
		} else {
			errMsg = "Please set action!";
		}

		// 返回异常信息
		sendBackXml(response, errMsg);
		*/
	}

	/**
	 * 处理数据采集客户端上传文件请求，保存文件，返回文件ID
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	private void doDataSubmitClientFileUpload(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 获取上传文件类型
		String contentType = request.getContentType();
		String fileType = contentType.substring(contentType.indexOf("___") + 3);
		String xmlResult = null;

		if (fileType == null || fileType.trim().length() == 0) {
			xmlResult = makeupReturnXmlText(false, "Please set FileType!");
		} else {
			try {
				DiskFileUpload dfu = new DiskFileUpload();
				dfu.setSizeMax(1024 * 1024 * 100); // 最大保存100M的文件
				dfu.setSizeThreshold(1024 * 1024 * 20); // 内存中最大20M

				List fileList = dfu.parseRequest(request);
				Iterator it = fileList.iterator();
				for (; it.hasNext();) {
					FileItem item = (FileItem) it.next();
					if (!item.isFormField()) {
						// 保存第一个文件，约定客户端不采用批量方式上传文件，
						String fileName = fileType + "_"
								+ System.currentTimeMillis();
						// 写文件
						String fullFileName = _pathTempFile + "/" + fileName;
						item.write(new File(fullFileName));
						break;
					}
				}
				xmlResult = makeupReturnXmlText(true, "");
			} catch (Exception e) {
				e.printStackTrace();
				xmlResult = makeupReturnXmlText(false,
						"Failed to save upload file!");
			}
		}
		// 返回结果 XML
		PrintWriter out = response.getWriter();
		out.write(xmlResult);
		out.flush();
		out.close();
	}

	/**
	 * 构造返回信息报文
	 * 
	 * @param isSuccess
	 * @param contents
	 * @return
	 */
	private String makeupReturnXmlText(boolean isSuccess, String contents) {
		StringBuffer strBuf = new StringBuffer();
		// 拼接成功报文
		if (isSuccess) {
			strBuf.append(UtilConstants.XML_ROOT_HEAD)
					.append(UtilConstants.XML_RETURN_SUCCESS).append(contents)
					.append(UtilConstants.XML_ROOT_TAIL);
		}// 拼接失败报文
		else {
			strBuf.append(UtilConstants.XML_ROOT_HEAD)
					.append(UtilConstants.XML_RETURN_FAILED)
					.append(UtilConstants.XML_ERR_MSG_HEAD).append(contents)
					.append(UtilConstants.XML_ERR_MSG_TAIL)
					.append("<BODY RtnBizCode=\"SystemFail\" RtnBizMessage=\""+contents+"\"/>")
					.append(UtilConstants.XML_ROOT_TAIL);
		}
		return strBuf.toString();
	}

	/**
	 * 处理 XML 报文方式的请求
	 * 
	 * @param request
	 * @param response
	 */
	private void doClientXml(HttpServletRequest request,
			HttpServletResponse response) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String msg = null;
		try {
			// 将 request 中的内容读取到 ByteArrayOutputStream
			int ch = request.getInputStream().read();
			while (ch != -1) {
				baos.write(ch);
				ch = request.getInputStream().read();
			}
			// 响应客户端请求
			responseClientXml(request, response, baos);
			return;
		} catch (FrameException ex) {
			msg = ex.getMessage();
		} catch (Exception e) {
			try {
				UtilLogger.logErr(e, baos.toString("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				UtilLogger.logErr(e, baos.toString());
			}
			msg = "XML文件格式错误或系统异常！";
		}

		String xmlText = makeupReturnXmlText(false, msg);
		// 发送错误信息
		sendBackXml(response, xmlText);
	}

	/**
	 * 响应客户端请求处理
	 * 
	 * @param request
	 * @param response
	 * @param bais
	 * @throws Exception
	 */
	private void responseClientXml(HttpServletRequest request,
			HttpServletResponse response, ByteArrayOutputStream baos)
			throws Exception {
		// debug 模式，输出请求报文
		if (_isDebug) {
			System.out.println("原始请求报文：");
			System.out.println(baos.toString("UTF-8"));
		}

		// 构造 ClientMessage
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		ClientMessage clientMsg = getClientMessage(bais, request);
		clientMsg.setXmlText(baos.toString("UTF-8"));

		// debug 模式，显示当前用户id，以及格式化的 XML
		if (_isDebug) {
			System.out.println("当前用户: "
					+ (clientMsg.getUserData() == null ? "" : clientMsg.getYhid()));
			System.out.println("格式化后的请求报文：");
			System.out.println(FetchXmlValueTool.formatXmlText(clientMsg.getXmlText()));
		}
		
		// 检查当前用户是否拥有请求的操作权限
		if (!checkPrivilege(clientMsg)) {
			// 没有权限，构造返回XML
			StringBuffer strBuf = new StringBuffer();
			strBuf.append(UtilConstants.XML_ROOT_HEAD).append(UtilConstants.XML_RETURN_FAILED)
				.append(UtilConstants.XML_ERR_MSG_HEAD).append("您没有访问该业务的操作权限")
				.append("<BODY RtnBizCode=\"NoPermission\" RtnBizMessage=\"您没有访问该业务的操作权限\"/>")
				.append(UtilConstants.XML_ERR_MSG_TAIL).append(UtilConstants.XML_ROOT_TAIL);
			sendBackXml(response, strBuf.toString());
		} else {
			// 分发处理
			String xmlResult = dispatchRequestAction(clientMsg);

			// debug 模式，输出返回报文
			if (_isDebug) {
				System.out.println("原始返回报文：");
				System.out.println(xmlResult);
				System.out.println("格式化后的返回报文：");
				System.out.println(FetchXmlValueTool.formatXmlText(xmlResult));
			}
			// 发送结果
			sendBackXml(response, xmlResult);
		}
	}

	/**
	 * 检查请求是否具有指定的操作权限
	 * @param clientMsg
	 * @return
	 */
	private boolean checkPrivilege(ClientMessage clientMsg) {
		// 是否检查权限
		if (!_checkPrivilege) return true;
		
		// 对于配置为不需要登录的功能，检查是否登录
		if (clientMsg.getUserData() == null) return false;
		
		// 获取当前用户 UserData 中的所有客户端权限
		List<qxgl_client> listQx = clientMsg.getUserData().getBusinessPrivilege();
		// 循环匹配请求的权限
		for (qxgl_client voQx : listQx) {
			if (voQx.getBussinessKey().equals(clientMsg.getType()) &&
					voQx.getAction().equals(clientMsg.getAction())) return true;
		}
		
		return false;
	}

	/**
	 * 从 ByteArrayInputStream 中构造 ClientMessage
	 * 
	 * @param doc
	 * @param request
	 * @return
	 * @throws FrameException
	 */
	private ClientMessage getClientMessage(ByteArrayInputStream bais,
			HttpServletRequest request) throws Exception {
		// 声明解析报文所需的对象
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;

		// 解析报文
		docBuilder = dbf.newDocumentBuilder();
		Document doc = docBuilder.parse(bais);

		// 获取 RequestAction
		NodeList nlRequest = doc.getElementsByTagName("RequestAction");
		if (nlRequest.getLength() == 0)
			throw new FrameException("Error: Failed to find <RequestAction>.");

		// 构造 ClientMessage
		Element requestInfo = (Element) nlRequest.item(0);
		ClientMessage clientMsg = new ClientMessage();
		clientMsg.setType(requestInfo.getAttribute("Type"));
		clientMsg.setAction(requestInfo.getAttribute("Action"));
		clientMsg.setPrivilege(requestInfo.getAttribute("Privilege"));

		clientMsg.setDoc(doc);
		clientMsg.setUserData((UserData) request.getSession(true).getAttribute(
				ValConstant.SESSION_USERDATA));

		return clientMsg;
	}

	/**
	 * 分发请求的 Action
	 * 
	 * @param clientMsg
	 * @return
	 */
	private String dispatchRequestAction(ClientMessage clientMsg) {
		IActionHandler handler = null;
		// 根据类型实例化对应的类
		String className = clientMsg.getType();
		if (className.indexOf(".") == -1) {
			// 临时版本，为了兼容老版本程序，老版本程序没有加前缀
			className = "com.lscdz.hlwdsj.application.presentation.handler." + className;
		}
		try {
			@SuppressWarnings("rawtypes")
			Class handlerClass = Class.forName(className);
			handler = (IActionHandler) handlerClass.newInstance();
		} catch (Exception e) {
			UtilLogger.logErr(e, "Can not create instance of " + className);
		}
		// 调用接口方法处理业务逻辑
		if (handler != null) {
			try {
				// 获取业务处理结果，再封装 XML 头和其它内容，返回
				StringBuffer strBufContent = handler.processMsg(clientMsg);
				return makeupReturnXmlText(true, strBufContent.toString());
			} catch (FrameException e) {
				// 发生异常，构造异常返回XML
				return makeupReturnXmlText(false, e.getMessage());
			}
		} else {
			// ActionHandler实例化异常，返回XML
			return makeupReturnXmlText(false, "请求资源访问错误");
		}
	}

	/**
	 * 向客户端写 xml 结果
	 * 
	 * @param response
	 * @param xmlText
	 */
	private void sendBackXml(HttpServletResponse response, String xmlText) {
		response.setContentType(CONTENT_TYPE_XML);
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			// out.write(Tools.toISO8859_1(xmlText.toString()));
			out.write(xmlText.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			UtilLogger.logErr(e, "Error：ClientActionServlet.sendBackXml();");
		}
	}

	/**
	 * 发送文件内容
	 * 
	 * @param agentMsg
	 */
	/*
	private void writeFileData(HttpServletResponse response, String filename) {
		// 发送文件数据
		response.setContentType(CONTENT_TYPE_APPLICATION_STREAM);
		BufferedInputStream bis = null;
		ServletOutputStream sos = null;
		try {

			bis = new BufferedInputStream(new FileInputStream(filename));
			sos = response.getOutputStream();

			// 循环读写
			int bytesRead = 0;
			final int len = 16384;
			byte[] buffer = new byte[len];
			bytesRead = bis.read(buffer, 0, len);

			// 写数据
			while (bytesRead != -1) {
				sos.write(buffer, 0, bytesRead);
				bytesRead = bis.read(buffer, 0, len);
			}
		} catch (Exception ex) {
			UtilLogger.logErr("Send file data failed.");
		} finally {
			if (sos != null) {
				try {
					sos.flush();
					sos.close();
				} catch (Exception se) {
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (Exception xe) {
				}
			}
		}
	}
	*/
}
