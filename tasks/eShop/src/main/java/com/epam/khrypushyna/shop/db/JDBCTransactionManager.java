package com.epam.khrypushyna.shop.db;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCTransactionManager implements TransactionManager {

	@Override
	public Object doTransaction(TransactionOperation operation) {
		Object result = null;
		Connection connection = null;
		try {
			connection = JDBCManager.getConnection();
			JDBCConnectionHolder.setConnection(connection);
			result = operation.execute();
			connection.commit();
		} catch (SQLException | NamingException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException ex) {
					System.err.println("SQL Exception while rollback in catch block. " + ex);
				}
			}
		} finally {
			JDBCConnectionHolder.removeConnection();
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.err.println("SQL Exception during closing connection. " + e);
				}
			}
		}

		return result;
	}

}
