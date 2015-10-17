package com.weizhu.officialAccounts.server;

import com.google.common.io.Resources;

public class Test {

	public static void main(String[] args) throws Exception {
		
		String war = "/Users/lindongjlu/Develop/git/weizhu_server/webapp/demo/target/weizhu-demo-webapp-1.0.0-SNAPSHOT.war";
		String confFile = Resources.getResource("com/weizhu/server/webapp/test/server.conf").getFile();
		String tmpDir = "./tmp";
		
		System.setProperty("server.conf", confFile);
		System.setProperty("server.tmp", tmpDir);
		
		Main.main(new String[]{war});
	}
	
}
