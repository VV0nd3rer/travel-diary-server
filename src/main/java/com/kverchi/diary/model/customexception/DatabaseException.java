package com.kverchi.diary.model.customexception;

import javax.persistence.PersistenceException;
import com.kverchi.diary.service.user.impl.MsgServiceResponse;

public class DatabaseException extends PersistenceException {

	private static final long serialVersionUID = 1L;
    String msg;
	
	public DatabaseException(Throwable t) {
		super(t);
		this.msg = MsgServiceResponse.TRANSACTION_PROBLEM.toString();
	}
	public String getMsg() {
		return this.msg;
	}
}
