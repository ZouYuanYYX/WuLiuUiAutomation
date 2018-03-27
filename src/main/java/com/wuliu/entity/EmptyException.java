package com.wuliu.entity;

/**
 * 自定义异常，空值异常
 * @author joy 
 * @date 2018.03.21
 */
public class EmptyException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyException (String message) {
		super(message);
	}
	
}
