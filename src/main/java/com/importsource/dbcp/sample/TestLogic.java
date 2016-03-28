package com.importsource.dbcp.sample;

import java.sql.Connection;

import com.importsource.dbcp.client.DBClient;
import com.importsource.dbcp.client.DBLogic;

class TestLogic extends DBLogic{

		@Override
		protected void onExecute(Connection conn) {
			String name = (String) DBClient.single(conn, "select name from user where name=?", "贺卓凡");
			System.out.println(name);
			String name1 = (String) DBClient.single(conn, "select name from user where name=?", "贺卓凡");
			System.out.println(name1);
		}
		
	}