package com.weizhu.officialAccounts.common.service.exception;

@SuppressWarnings("serial")
public class HeadUnknownException extends ServiceException {

	public HeadUnknownException() {
		super();
	}

	public HeadUnknownException(String message, Throwable cause) {
		super(message, cause);
	}

	public HeadUnknownException(String message) {
		super(message);
	}

	public HeadUnknownException(Throwable cause) {
		super(cause);
	}

}
