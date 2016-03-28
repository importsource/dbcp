package com.importsource.dbcp.sample;

import java.sql.Connection;
import java.sql.SQLException;

import com.importsource.dbcp.client.DBClient;
import com.importsource.dbcp.core.DbcpConnection;

public class DbcpDemo {
	/*public static void main(String[] args) throws SQLException {
		for (int i = 0; i < 100000; i++) {
			Connection conn = DbcpConnection.getConnection();
			String name = (String) DBClient.single(conn, "select name from user where name=?", "贺卓凡");
			System.out.println(name);
			String name1 = (String) DBClient.single(conn, "select name from user where name=?", "贺卓凡");
			System.out.println(name1);
			conn.close();

		}

	}*/
}
