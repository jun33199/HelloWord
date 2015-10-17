package com.weizhu.officialAccounts.common.service.exception;

@SuppressWarnings("serial")
public class ResponseParseException extends ServiceException {

	public ResponseParseException() {
		super();
	}

	public ResponseParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResponseParseException(String message) {
		super(message);
	}

	public ResponseParseException(Throwable cause) {
		super(cause);
	}

}
