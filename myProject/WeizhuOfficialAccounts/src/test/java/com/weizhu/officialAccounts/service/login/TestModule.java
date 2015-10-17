package com.weizhu.officialAccounts.service.login;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.weizhu.officialAccounts.common.service.ServiceThread;

/**
 * test 对应的资源
 * @author lindongjlu
 *
 */
public class TestModule extends AbstractModule {

	@Override
	protected void configure() {
		
	}
	
	
	@Provides
	@Singleton
	@Named("service_executor")
	public Executor provideServiceExecutor() {
		return Executors.newCachedThreadPool(new ServiceThread.Factory());
	}
	
	@Provides
	@Singleton
	@Named("service_scheduled_executor")
	public ScheduledExecutorService provideScheduledExecutorService() {
		return Executors.newScheduledThreadPool(1);
	}

//	@Provides
//	@Singleton
//	public JedisPool provideJedisPool() {
//		return new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6379, 2000, null, 0);
//	}
//	
//	@Provides
//	@Singleton
//	public HikariDataSource provideHikariDataSource() {
//		HikariConfig config = new HikariConfig();
//		config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/weizhu_test?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&autoReconnect=true&failOverReadOnly=false");
//		config.setUsername("root");
//		// config.setPassword("");
//		config.setMaximumPoolSize(3);
//		config.addDataSourceProperty("cachePrepStmts", "true");
//		config.addDataSourceProperty("prepStmtCacheSize", "250");
//		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//		config.addDataSourceProperty("useServerPrepStmts", "true");
//		return new HikariDataSource(config);
//	}
//	
//	@Provides
//	@Singleton
//	@Named("service_conf")
//	public Properties provideConfProperties() {
//		Properties confProperties = new Properties();
//		
//		// admin service
//		confProperties.put("admin_secret_key", "012345678901234567890123");
//		
//		// login service
//		confProperties.put("login_fake_sms_code", "true");
//		confProperties.put("login_send_sms_enable", "false");
//		confProperties.put("login_send_sms_url", "");
//		confProperties.put("login_send_sms_proxy", "");
//		
//		// official service
//		confProperties.put("official_weizhu_secretary_v5kf_enable", "false");
//		confProperties.put("official_weizhu_secretary_v5kf_url", "");
//		confProperties.put("official_weizhu_secretary_v5kf_proxy", "");
//		confProperties.put("official_weizhu_secretary_login_sayhello", "");
//		
//		// session service
//		confProperties.put("session_secret_key", "012345678901234567890123");
//		
//		// system service
//		confProperties.put("system_config_http_api_url", "http://218.241.220.36:8099/api/pb");
//		confProperties.put("system_config_socket_connection_host", "218.241.220.36");
//		confProperties.put("system_config_socket_connection_port", "8098");
//		confProperties.put("system_config_upload_avatar_url", "http://218.241.220.36:8097/upload/avatar");
//		confProperties.put("system_config_upload_im_image_url", "http://218.241.220.36:8097/upload/im/image");
//		confProperties.put("system_config_upload_im_file_url", "http://218.241.220.36:8097/upload/im/file");
//		confProperties.put("system_config_avatar_url", "http://218.241.220.36:8096/avatar/");
//		confProperties.put("system_config_im_image_url", "http://218.241.220.36:8096/im/image/");
//		confProperties.put("system_config_im_file_url", "http://218.241.220.36:8096/im/file/");
//		confProperties.put("system_config_discover_image_url", "http://218.241.220.36:8096/discover/image/");
//		confProperties.put("system_config_discover_icon_url", "http://218.241.220.36:8096/discover/image/");
//		confProperties.put("system_config_discover_item_url", "http://218.241.220.36:8099/mobile/discover/item_content?item_id=");
//		confProperties.put("system_config_upload_community_image_url", "http://218.241.220.36:8097/upload/community/image");
//		confProperties.put("system_config_community_image_url", "http://218.241.220.36:8096/community/image/");
//
//		confProperties.put("system_new_version_company_id_list", "1");
//		confProperties.put("system_new_version_1_android_version_name", "0.1.0");
//		confProperties.put("system_new_version_1_android_feature_text", "哈哈");
//		confProperties.put("system_new_version_1_android_download_url", "http://wehelpu.cn/download");
//		confProperties.put("system_new_version_1_android_version_code", "123");
//		confProperties.put("system_new_version_1_iphone_version_name", "0.1.0");
//		confProperties.put("system_new_version_1_iphone_feature_text", "哈哈");
//		confProperties.put("system_new_version_1_iphone_download_url", "http://wehelpu.cn/download");
//		confProperties.put("system_new_version_1_iphone_version_code", "123");
//		
//		// discover service
//		confProperties.put("discover_extends_exam_module_id", "7");
//		confProperties.put("discover_extends_exam_item_icon_name", "");
//		
//		return confProperties;
//	}
	
}
