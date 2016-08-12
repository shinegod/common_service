package com.fx.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VitasException extends Exception {
	Logger logger = LoggerFactory.getLogger(getClass());
	public VitasException(String msg){
		super(msg);
		logger.error(msg);
	}
}
