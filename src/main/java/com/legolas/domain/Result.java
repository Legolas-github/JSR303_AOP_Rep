package com.legolas.domain;


public class Result<T> {
	private boolean flag;
	private String code;
	private T message;
	
	
	public Result() {
		super();
	}
	public Result(boolean flag, String code, T message) {
		super();
		this.flag = flag;
		this.code = code;
		this.message = message;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public T getMessage() {
		return message;
	}
	public void setMessage(T message) {
		this.message = message;
	}
	
	public static Result<String> fail(String message){
		return new Result<>(false, "-1", message);
	}
	
	public static <T> Result<T> success(T message){
		return new Result<>(true,"0",message);
	}
	
}
