package com.smart.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smart.domain.LoginLog;

@Repository
public class LoginLogDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertLoginLog(LoginLog loginLog) {
		jdbcTemplate.update("Insert into t_login_log(user_id,ip,login_datetime) values(?,?,?);",
				new Object[] { loginLog.getUserId(), loginLog.getIp(), loginLog.getLoginDate() });
	}
}
