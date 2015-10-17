package com.weizhu.officialAccounts.common.utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.google.inject.util.Types;
import com.zaxxer.hikari.HikariDataSource;

public class TestUtil {

	public static void clearCache(Injector injector) {
		final JedisPool jedisPool = injector.getInstance(JedisPool.class);

		Jedis jedis = jedisPool.getResource();
		try {
			jedis.flushDB();
		} finally {
			jedis.close();
		}
	}

	@SuppressWarnings("unchecked")
	public static void createTableDB(Injector injector) {

		final HikariDataSource hikariDataSource = injector.getInstance(HikariDataSource.class);
		final Set<String> createTableSQLSet = (Set<String>) injector.getInstance(Key.get(Types.setOf(String.class),
				Names.named("db_create_table.sql")));

		Connection dbConn = null;
		Statement stmt = null;
		;
		try {
			dbConn = hikariDataSource.getConnection();
			stmt = dbConn.createStatement();
			for (String sqlFile : createTableSQLSet) {
				stmt.execute(Resources.toString(Resources.getResource(sqlFile), Charsets.UTF_8));
			}
		} catch (SQLException e) {
			throw new Error(e);
		} catch (IOException e) {
			throw new Error(e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// ignore
				}
			}
			if (dbConn != null) {
				try {
					dbConn.close();
				} catch (SQLException e) {
					// ignore
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void dropTableDB(Injector injector) {

		final HikariDataSource hikariDataSource = injector.getInstance(HikariDataSource.class);
		final Set<String> dropTableSQLSet = (Set<String>) injector.getInstance(Key.get(Types.setOf(String.class), Names.named("db_drop_table.sql")));

		Connection dbConn = null;
		Statement stmt = null;
		;
		try {
			dbConn = hikariDataSource.getConnection();
			stmt = dbConn.createStatement();
			for (String sqlFile : dropTableSQLSet) {
				stmt.execute(Resources.toString(Resources.getResource(sqlFile), Charsets.UTF_8));
			}
		} catch (SQLException e) {
			throw new Error(e);
		} catch (IOException e) {
			throw new Error(e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// ignore
				}
			}
			if (dbConn != null) {
				try {
					dbConn.close();
				} catch (SQLException e) {
					// ignore
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadTestDataDB(Injector injector) {

		final HikariDataSource hikariDataSource = injector.getInstance(HikariDataSource.class);

		final Type stringSetType = Types.setOf(String.class);
		final Set<String> dropTableSQLSet = (Set<String>) injector.getInstance(Key.get(stringSetType, Names.named("db_drop_table.sql")));
		final Set<String> createTableSQLSet = (Set<String>) injector.getInstance(Key.get(stringSetType, Names.named("db_create_table.sql")));
		final Set<String> testDataSQLSet = (Set<String>) injector.getInstance(Key.get(stringSetType, Names.named("db_test_data.sql")));

		Connection dbConn = null;
		Statement stmt = null;
		;
		try {
			dbConn = hikariDataSource.getConnection();
			stmt = dbConn.createStatement();
			for (String sqlFile : dropTableSQLSet) {
				stmt.execute(Resources.toString(Resources.getResource(sqlFile), Charsets.UTF_8));
			}
			for (String sqlFile : createTableSQLSet) {
				stmt.execute(Resources.toString(Resources.getResource(sqlFile), Charsets.UTF_8));
			}
			for (String sqlFile : testDataSQLSet) {
				stmt.execute(Resources.toString(Resources.getResource(sqlFile), Charsets.UTF_8));
			}
		} catch (SQLException e) {
			throw new Error(e);
		} catch (IOException e) {
			throw new Error(e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// ignore
				}
			}
			if (dbConn != null) {
				try {
					dbConn.close();
				} catch (SQLException e) {
					// ignore
				}
			}
		}
	}

}
