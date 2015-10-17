package com.weizhu.officialAccounts.common.service.exception;

@SuppressWarnings("serial")
public class RequestParseException extends ServiceException {

	public RequestParseException() {
		super();
	}

	public RequestParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequestParseException(String message) {
		super(message);
	}

	public RequestParseException(Throwable cause) {
		super(cause);
	}

}
