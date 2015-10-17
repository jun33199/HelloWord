package com.weizhu.officialAccounts.service.login;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.weizhu.officialAccounts.common.proto.LoginService;
import com.weizhu.officialAccounts.proto.LoginProtos;

import junit.framework.TestCase;

public class LoginServiceImpTest extends TestCase {

	static {
		System.setProperty("logback.configurationFile", "com/weizhu/service/officialAccounts/test/logback.xml");
	}
	private static final Injector INJECTOR = Guice.createInjector(new TestModule(), new LoginServiceModule());
	
//	@BeforeClass
//	public static void init() throws Exception {
//		TestUtil.clearCache(INJECTOR);
//		TestUtil.loadTestDataDB(INJECTOR);
//	}
	private final LoginService loginService;

	public LoginServiceImpTest() {
		this.loginService = INJECTOR.getInstance(LoginService.class);
	}

	@Test
	public void testLogin() throws Exception {

		LoginProtos.LoginResponse response = loginService.login(LoginProtos.LoginRequest.newBuilder()
				.setUserName("junzhang")
				.setPassword("junzhang123")
				.build()).get();
		
		assertEquals(LoginProtos.LoginResponse.Result.SUCC, response.getResult());
	}
}
