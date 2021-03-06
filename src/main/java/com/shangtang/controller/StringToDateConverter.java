package com.shangtang.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

public class StringToDateConverter implements Converter<String, Date> {

	private DateFormat df;

	public StringToDateConverter(String pattern) {
		df = new SimpleDateFormat(pattern);
	}

	@Override
	public Date convert(String source) {
		if (StringUtils.hasText(source)) {
			try {
				return df.parse(source);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}