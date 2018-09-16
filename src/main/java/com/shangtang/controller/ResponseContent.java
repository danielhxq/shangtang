package com.shangtang.controller;

import java.io.Serializable;

public class ResponseContent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4080754111859165132L;

	private String code;

	private String msg;

	private String data;

	public ResponseContent() {
	}

	public ResponseContent(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseContent [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

}
