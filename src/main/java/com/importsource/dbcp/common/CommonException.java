package com.importsource.dbcp.common;

/**
 * 
 * 
 * @author hezf
 * 
 */
public class CommonException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	/**
	 * exception code
	 */
	protected String code;
	
	public CommonException(String code,String message){
		super(message);
		this.code = code;
	}
	
	public CommonException(String code,String message,Exception source){
		super(message + "->" + source.getMessage());
		this.code = code;
	}
	
	public String getCode(){return code;}
}
