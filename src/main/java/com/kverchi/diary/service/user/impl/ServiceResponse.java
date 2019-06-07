package com.kverchi.diary.service.user.impl;
import org.springframework.http.HttpStatus;

public class ServiceResponse<T> {
	
	private HttpStatus responseCode;
	private MsgServiceResponse responseMessage;
	private T responseObject;
	
	public ServiceResponse() {}
	public ServiceResponse(HttpStatus code, MsgServiceResponse msg) {
		this.responseCode = code;
		this.responseMessage = msg;
	}
	public ServiceResponse(HttpStatus code, MsgServiceResponse msg, T responseObject) {
		this.responseCode = code;
		this.responseMessage = msg;
		this.responseObject = responseObject;
	}
	public HttpStatus getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(HttpStatus responseCode) {
		this.responseCode = responseCode;
	}
	public MsgServiceResponse getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(MsgServiceResponse responseMessage) {
		this.responseMessage = responseMessage;
	}
	public T getResponseObject() {
		return responseObject;
	}
	public void setResponseObject(T responseObject) {
		this.responseObject = responseObject;
	}

	public void setSuccessResponse() {
		this.setResponseCode(HttpStatus.OK);
		this.setResponseMessage(MsgServiceResponse.OK);
	}

	public void setInternalServerErrorResponse() {
		this.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR);
		this.setResponseMessage(MsgServiceResponse.TRANSACTION_PROBLEM);
	}
}
