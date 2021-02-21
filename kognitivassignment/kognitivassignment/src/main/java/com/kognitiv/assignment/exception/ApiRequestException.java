package com.kognitiv.assignment.exception;

public class ApiRequestException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1810006103649263514L;
	public ApiRequestException(String msg) {
		super(msg);
	}
	public ApiRequestException(String msg,Throwable e) {
		super(msg,e);
	}
}
