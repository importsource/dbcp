package com.importsource.dbcp.client;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.importsource.dbcp.common.CommonException;
import com.importsource.dbcp.core.DbcpConnection;

/**
 * 
 * 客户端的逻辑类可以继承该类实现支持事务的业务逻辑。
 * @author hezf
 *
 */
public abstract class DBLogic {
	protected static final Logger logger = LogManager.getLogger(DBLogic.class);

	protected Connection conn = null;

	protected void setConnection() {
		conn = DbcpConnection.getConnection();
	}

	public int onExecute() throws CommonException {

		setConnection();
		if (conn == null) {
			logger.error("The database connection is null!");
			return -1;
		}
		boolean autoCommit = true;
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			onExecute(conn);
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
			logger.error(ex.getMessage(), ex);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new CommonException("sql_exception", "Dbcp connection can't be closed");
			}
		}
		return 0;
	}

	protected abstract void onExecute(Connection conn);
}
