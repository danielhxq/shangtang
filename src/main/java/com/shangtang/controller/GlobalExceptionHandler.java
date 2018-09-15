package com.shangtang.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mongodb.DuplicateKeyException;

@ControllerAdvice
public class GlobalExceptionHandler {
//
//	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
//	
//	@ExceptionHandler(SQLException.class)
//	public String handleSQLException(HttpServletRequest request, Exception ex){
//		logger.info("SQLException Occured:: URL="+request.getRequestURL());
//		return "database_error";
//	}

	@ExceptionHandler(DuplicateKeyException.class)
	public void handleException(DuplicateKeyException ex) {
//		logger.info("SQLException Occured:: URL="+ex.getErrorCode());
//		return ex.getResponse().toJson();

	}

//	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="IOException occured")
//	@ExceptionHandler(IOException.class)
//	public void handleIOException(){
//		logger.error("IOException handler executed");
//		//returning 404 error code
//	}
}