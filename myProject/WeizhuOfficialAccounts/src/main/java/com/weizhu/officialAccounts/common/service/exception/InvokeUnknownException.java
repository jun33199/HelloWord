package com.weizhu.officialAccounts.common.service.exception;


@SuppressWarnings("serial")
public class InvokeUnknownException extends ServiceException {

	public InvokeUnknownException() {
		super();
	}

	public InvokeUnknownException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvokeUnknownException(String message) {
		super(message);
	}

	public InvokeUnknownException(Throwable cause) {
		super(cause);
	}

}
