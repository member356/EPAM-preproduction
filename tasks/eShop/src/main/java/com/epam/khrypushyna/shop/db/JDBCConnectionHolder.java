package com.epam.khrypushyna.shop.db;

import java.sql.Connection;

public class JDBCConnectionHolder {
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

	public static Connection getConnection() {
		return threadLocal.get();
	}

	public static void setConnection(Connection connection) {
		threadLocal.set(connection);
	}

	public static void removeConnection() {
		threadLocal.remove();
	}
}
