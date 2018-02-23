package com.epam.khrypushyna.shop.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCManager {
	private static DataSource dataSource;

	static {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/" + "jdbc/database");
		} catch (NamingException e) {
			throw new RuntimeException("Fail init data source.", e);
		}
	}


	public static Connection getConnection() throws NamingException, SQLException {
		return dataSource.getConnection();
	}
}
