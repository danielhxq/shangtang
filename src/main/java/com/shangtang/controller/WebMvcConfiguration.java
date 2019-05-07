package com.shangtang.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;

@Configuration
public class WebMvcConfiguration {

	@Bean
	public ConversionService conversionService() {
		ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
		Set<Converter<?, ?>> converterList = new HashSet<Converter<?, ?>>();
		converterList.add(new FaceReportsConverter());
		converterList.add(new FaceReportNoImageConverter());
		bean.setConverters(converterList);
		bean.afterPropertiesSet();
		return bean.getObject();
	}

	@Bean
	public LoggingEventListener mappingEventsListener() {
		return new LoggingEventListener();
	}
}
