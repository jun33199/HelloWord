package com.smart.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.smart.domain.User;

@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int getMatchCount(String userName, String password) {
		return jdbcTemplate.queryForInt(" SELECT count(*) FROM t_user WHERE user_name =? AND password=? ;", new Object[] { userName, password });
	}

	public User findUserByUserName(final String userName) {

		final User user = new User();
		jdbcTemplate.query("select user_id,user_name from t_user where user_name = ? ;", new Object[] { userName }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
			}
		});

		return user;
	}

	public void updateLoginInfo(User user) {

		jdbcTemplate.update("update t_user set last_visit =?,last_ip=? where user_id=?;",
				new Object[] { user.getLastVisit(), user.getLastIp(), user.getUserId() });
	}
}
