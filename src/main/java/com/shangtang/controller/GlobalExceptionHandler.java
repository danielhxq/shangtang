package com.shangtang.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mongodb.DuplicateKeyException;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<ResponseContent> handleException(DuplicateKeyException ex) {
		logger.error("DuplicateKeyException Occured::" + ex.getMessage());
		String str = ex.getMessage();
		str = str.substring(str.indexOf("key:") + 4, str.indexOf("}'") + 1);
		String[] ss = str.split(":");
		final StringBuilder sb = new StringBuilder();
		ResponseContent rc = new ResponseContent();
		rc.setCode("E11000");
		rc.setMsg("Duplicate Key Exception");
		if (ss != null && ss.length == 4) {
			sb.append(ss[0]).append("group_id:").append(ss[1]).append("person_id:").append(ss[2]).append("timestamp:")
					.append(ss[3]);
			rc.setData(sb.toString());
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(rc);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(rc);
	}

//	@ExceptionHandler(DuplicateKeyException.class)
//	public void handleException(DuplicateKeyException ex) {
//		logger.info("SQLException Occured:: URL="+ex.getErrorCode());
//		return ex.getResponse().toJson();

//	}

//	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="IOException occured")
//	@ExceptionHandler(IOException.class)
//	public void handleIOException(){
//		logger.error("IOException handler executed");
//		//returning 404 error code
//	}
}