package com.importsource.dbcp.client;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.mariadb.jdbc.internal.common.packet.buffer.Reader;

import com.importsource.dbcp.core.Read;
import com.importsource.dbcp.core.Write;

/**
 * 客户端访问接口
 * @author Hezf
 *
 */
public class DBClient {
	public static List<Map<String, Object>> list(Connection conn, String sql, Object... params) {
		Read read=new Read(conn);
		read=read.execute(sql, params);
		return read.list();
	}
	
	public static int insert(Connection conn, String sql, Object... params){
		Write write=new Write(conn);
		return write.execute(sql, params);
	}
	
	public static int update(Connection conn, String sql, Object... params){
		Write write=new Write(conn);
		return write.execute(sql, params);
	}
	
	public static int del(Connection conn, String sql, Object... params){
		Write write=new Write(conn);
		return write.execute(sql, params);
	}

	public static Object single(Connection conn, String sql, String params) {
		Read read=new Read(conn);
		read=read.execute(sql, params);
		return read.single();
	}

	

}
