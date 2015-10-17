package com.weizhu.officialAccounts.common.proto;

import com.google.common.util.concurrent.ListenableFuture;
import com.weizhu.officialAccounts.proto.LoginProtos.LoginRequest;
import com.weizhu.officialAccounts.proto.LoginProtos.LoginResponse;


public interface LoginService {

	@ResponseType(LoginResponse.class)
	ListenableFuture<LoginResponse> login(LoginRequest request);

}
