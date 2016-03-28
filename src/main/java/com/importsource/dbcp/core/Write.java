package com.importsource.dbcp.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.importsource.dbcp.common.CommonException;


/**
 * 负责所有的写入操作。比如inert,update,del
 * @author Hezf
 *
 */
public class Write extends Operation {

	public Write(Connection conn) {
		super(conn);
	}
	
	
	/**
	 * 执行单个SQL语句
	 * @param sql 
	 * @param params 参数列表
	 * @return
	 * @throws CommonException
	 */
	public int execute(String sql,Object...params) throws CommonException{
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			
			if (params != null){
				for (int i = 0 ; i < params.length ; i ++){
					stmt.setObject(i + 1, params[i]);
				}
			}
			
			return stmt.executeUpdate();
		}catch (SQLException ex){
			throw new CommonException("core.sql_error","Error occurs when executing sql:" + ex.getMessage());
		}
		finally{
			close(stmt);
		}
	}
}
