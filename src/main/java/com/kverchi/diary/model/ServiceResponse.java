package com.kverchi.diary.model;

import com.kverchi.diary.model.enums.ServiceMessageResponse;
import org.springframework.http.HttpStatus;

public class ServiceResponse<T> {
	
	private HttpStatus responseCode;
	private ServiceMessageResponse responseMessage;
	private T responseObject;
	
	public ServiceResponse() {}
	public ServiceResponse(HttpStatus code, ServiceMessageResponse msg) {
		this.responseCode = code;
		this.responseMessage = msg;
	}
	public ServiceResponse(HttpStatus code, ServiceMessageResponse msg, T responseObject) {
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
	public ServiceMessageResponse getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(ServiceMessageResponse responseMessage) {
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
		this.setResponseMessage(ServiceMessageResponse.OK);
	}

	public void setInternalServerErrorResponse() {
		this.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR);
		this.setResponseMessage(ServiceMessageResponse.TRANSACTION_PROBLEM);
	}
}
