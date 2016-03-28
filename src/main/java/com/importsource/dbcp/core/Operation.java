package com.importsource.dbcp.core;

import java.sql.Connection;

/**
 * 数据库操作基类
 * 
 * @author Hezf
 *
 */
public abstract class Operation{
	protected Connection conn = null;

	public Operation(Connection conn) {
		this.conn = conn;
	}
 
	/**
	 * 关闭
	 * @param autoCloseables
	 */
	public  void close(AutoCloseable... autoCloseables) {
		for (AutoCloseable c : autoCloseables) {
			if (null != c) {
				try {
					c.close();
				} catch (Exception ex) {

				}
			}
		}
	}
	

}
