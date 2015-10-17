package com.weizhu.officialAccounts.service.login;

import java.util.concurrent.Executor;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.weizhu.officialAccounts.common.proto.LoginService;
import com.weizhu.officialAccounts.common.service.ServiceInvoker;
import com.weizhu.officialAccounts.common.service.ServiceStub;


public class LoginServiceModule extends AbstractModule {
	
	@Override
	protected void configure() {

		bind(LoginServiceImp.class).in(Singleton.class);

		Multibinder<String> createTableSQLBinder = Multibinder.newSetBinder(binder(), String.class, Names.named("db_create_table.sql"));
		createTableSQLBinder.addBinding().toInstance("com/weizhu/officialAccounts/db_create_table.sql");

		Multibinder<String> dropTableSQLBinder = Multibinder.newSetBinder(binder(), String.class, Names.named("db_drop_table.sql"));
		dropTableSQLBinder.addBinding().toInstance("com/weizhu/officialAccounts/db_drop_table.sql");
	}

	@Provides
	@Singleton
	public LoginService provideCommunityService(LoginServiceImp serviceImpl, @Named("service_executor") Executor serviceExecutor) {
		return ServiceStub.createServiceApi(LoginService.class, serviceImpl, serviceExecutor);
	}

	@Provides
	@Singleton
	@Named("LoginService")
	public ServiceInvoker provideCommunityServiceInvoker(LoginServiceImp serviceImpl, @Named("service_executor") Executor serviceExecutor) {
		return ServiceStub.createServiceInvoker(LoginService.class, serviceImpl, serviceExecutor);
	}


}
