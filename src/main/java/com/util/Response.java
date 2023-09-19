package com.util;

public class Response<T> {

	private Object message;

	private Object response;

	private Object status;

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public Object getStatus() {
		return status;
	}

	public void setStatus(Object status) {
		this.status = status;
	}

	public Response(Object message, Object response, Object status) {
		super();
		this.message = message;
		this.response = response;
		this.status = status;
	}

	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}

}
