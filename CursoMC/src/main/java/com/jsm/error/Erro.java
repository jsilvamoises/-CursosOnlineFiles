package com.jsm.error;

import java.io.Serializable;

public class Erro implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer statusCode;
	private String msg;
	private Long timestamp;
	
	

	public Erro(Integer statusCode, String msg, Long timestamp) {
		super();
		this.statusCode = statusCode;
		this.msg = msg;
		this.timestamp = timestamp;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

}
