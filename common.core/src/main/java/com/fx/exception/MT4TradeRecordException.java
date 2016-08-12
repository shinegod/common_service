package com.fx.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MT4TradeRecordException extends Exception {
	Logger logger = LoggerFactory.getLogger(getClass());
	public MT4TradeRecordException(String msg){
		super(msg);
		logger.error(msg);
	}
}
