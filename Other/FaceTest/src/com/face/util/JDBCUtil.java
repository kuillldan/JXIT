package com.face.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JDBCUtil {
	private Connection conn = null;

	public Connection getConnection() {
		if (null == conn) {
			init();
		}
		return conn;
	}

	private void init() {
		ResourceBundle resource = ResourceBundle.getBundle("jdbc");

		if (Boolean.valueOf(resource.getString("jdbc.jndi"))) {
			conn = getConnection(resource.getString("jdbc.jndiname"));
		} else {
			try {
				Class.forName(resource.getString("jdbc.driver"));
				conn = DriverManager.getConnection(resource.getString("jdbc.url"), resource.getString("jdbc.username"),
						resource.getString("jdbc.password"));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private Connection getConnection(String jndi) {
		DataSource datasource = null;
		Connection connection = null;
		try {
			Context context = new InitialContext();
			if (null != jndi && !"".equals(jndi)) {
				datasource = (DataSource) context.lookup(jndi);
			} else {
				datasource = (DataSource) context.lookup("sqlserver/default");
			}
			connection = datasource.getConnection();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return connection;
	}
}
