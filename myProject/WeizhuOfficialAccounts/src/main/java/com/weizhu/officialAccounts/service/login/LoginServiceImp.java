package com.weizhu.officialAccounts.service.login;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.weizhu.officialAccounts.common.proto.LoginService;
import com.weizhu.officialAccounts.common.service.SyncImpl;
import com.weizhu.officialAccounts.proto.LoginProtos;
import com.weizhu.officialAccounts.proto.LoginProtos.LoginRequest;
import com.weizhu.officialAccounts.proto.LoginProtos.LoginResponse;

public class LoginServiceImp implements LoginService {

	private static final String USER_NAME = "junzhang";
	private static final String PASSWORD = "junzhang123";

	@Override
	@SyncImpl
	public ListenableFuture<LoginResponse> login(LoginRequest request) {

		if (!USER_NAME.equals(request.getUserName())) {
			return Futures.immediateFuture(LoginProtos.LoginResponse.newBuilder()
					.setResult(LoginProtos.LoginResponse.Result.FAIL_USER_NOT_EXIST)
					.setFailText("用户不存在！")
					.build());
		} else if (!PASSWORD.equals(request.getPassword())) {
			return Futures.immediateFuture(LoginProtos.LoginResponse.newBuilder()
					.setResult(LoginProtos.LoginResponse.Result.FAIL_PASSWORD_IS_WRONG)
					.setFailText("密码不正确！")
					.build());
		} else {
			return Futures.immediateFuture(LoginProtos.LoginResponse.newBuilder().setResult(LoginProtos.LoginResponse.Result.SUCC).build());
		}
	}

}
