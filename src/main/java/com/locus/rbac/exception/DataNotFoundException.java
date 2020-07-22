package com.locus.rbac.exception;

public class DataNotFoundException extends RuntimeException {
	public DataNotFoundException(final String message) {
		super(message);
	}
}
