package com.importsource.dbcp.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.importsource.dbcp.common.CommonException;




/**
 * 负责所有的读取操作
 * 
 * @author Hezf
 *
 */
public class Read extends Operation {

	public Read(Connection conn) {
		super(conn);
	}

	protected ResultSet rs;

	protected PreparedStatement stmt = null;
	
	/**
	 * 得到句柄
	 * @return
	 */
	public PreparedStatement getStatement(){
		return this.stmt;
	}
	
	/**
	 * 得到resultset
	 * @return ResultSet
	 */
	public ResultSet getResultSet(){
		return this.rs;
	}

	/**
	 * 执行SQL语句
	 * 
	 * @param sql
	 *            SQL语句
	 * @param params
	 *            参数列表
	 * @return
	 * @throws CommonException
	 */
	public Read execute(String sql, Object... params) throws CommonException {
		close();
		try {
			stmt = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					stmt.setObject(i + 1, params[i]);
				}
			}
			rs = stmt.executeQuery();
			return this;
		} catch (SQLException ex) {
			throw new CommonException("core.sql_error", "Error occurs when executing sql:" + ex.getMessage());
		}
	}
	
	

	/**
	 * 获取查询结果（单返回值）
	 * 
	 * @return 结果值
	 * @throws SQLException
	 */
	public Object single() throws CommonException {
		try {
			if (rs != null && rs.next()) {
				return rs.getObject(1);
			}
			return null;
		} catch (SQLException ex) {
			throw new CommonException("core.sql_error", "Error occurs when executing sql:" + ex.getMessage());
		}finally{
			close(rs,stmt);
		}
	}
	
	
    /**
     * 得到列表
     * @return List<Map<String, Object>> 列表
     */
	public List<Map<String, Object>> list() {
		List<Map<String, Object>> lst;
		try {
			lst = new ArrayList<Map<String, Object>>();
			while (rs != null && rs.next()) {

				Map<String, Object> tuple = new HashMap<String, Object>();
				ResultSetMetaData metadata = rs.getMetaData();
				int columnCount = metadata.getColumnCount();

				for (int i = 0; i < columnCount; i++) {
					Object value = rs.getObject(i + 1);
					if (value == null)
						continue;
					String name = metadata.getColumnName(i + 1);
					if (name == null)
						continue;
					tuple.put(name, value);
				}

				lst.add(tuple);
			}
			return lst;
		} catch (SQLException ex) {
			throw new CommonException("core.sql_error","Error occurs when executing sql:" + ex.getMessage());
		}finally{
			close(rs,stmt);
		}
		

	}

}
