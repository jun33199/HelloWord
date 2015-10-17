package com.weizhu.officialAccounts.server;

import java.io.FileReader;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.util.Properties;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

import com.google.common.net.HostAndPort;

public class Main {

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			throw new Error("invalid arg : java -jar weizhu-webapp-server.jar [war file]");
		}
		
		final String war = args[0];
		
		// set log conf
		System.setProperty("logback.configurationFile", "com/weizhu/server/webapp/logback.xml");
		
		// 读取系统变量
		final String serverConf = System.getProperty("server.conf");
		final String serverTmp = System.getProperty("server.tmp"); // may be null
		
		// 读取server配置
		Properties confProp = new Properties();
		
		Reader confReader = new FileReader(serverConf);
		try {
			confProp.load(confReader);
		} finally {
			confReader.close();
		}

		final HostAndPort bindAddr = HostAndPort.fromString(confProp.getProperty("webapp_server_bind_addr"));
		final String contextPath = confProp.getProperty("webapp_context_path");
		
		// 创建 jetty server
		final Server server = new Server(new InetSocketAddress(bindAddr.getHostText(), bindAddr.getPort()));
		
		// 创建 jetty webapp
		final WebAppContext webapp = new WebAppContext();
		webapp.setWar(war);
		if (contextPath != null) {
			webapp.setContextPath(contextPath);
		}
		if (serverTmp != null) {
			webapp.setAttribute(WebAppContext.BASETEMPDIR, serverTmp);
		}
		
		// This webapp will use jsps and jstl. We need to enable the
		// AnnotationConfiguration in order to correctly
		// set up the jsp container
		Configuration.ClassList.setServerDefault(server).addBefore(
				"org.eclipse.jetty.webapp.JettyWebXmlConfiguration", 
				"org.eclipse.jetty.annotations.AnnotationConfiguration");
		
		// Set the ContainerIncludeJarPattern so that jetty examines these
		// container-path jars for tlds, web-fragments etc.
		// If you omit the jar that contains the jstl .tlds, the jsp engine will
		// scan for them instead.
		webapp.setAttribute(
				"org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
				".*/[^/]*servlet-api-[^/]*\\.jar$|.*/javax.servlet.jsp.jstl-.*\\.jar$|.*/[^/]*taglibs.*\\.jar$");
		
		server.setHandler(webapp);
		server.setStopAtShutdown(true);
		
		System.out.println("bindAddr=" + bindAddr);
		System.out.println("contextPath=" + contextPath);
		System.out.println("serverTmp=" + serverTmp);
		
		server.start();
		server.join();
	}

}
