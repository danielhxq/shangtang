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
		Set<Converter<FaceReports, FaceReportEntities>> converters = new HashSet<Converter<FaceReports, FaceReportEntities>>();
		converters.add(new FaceReportsConverter());
		// converters.add(new IntegerToDateConverter());
		bean.setConverters(converters);
		bean.afterPropertiesSet();
		return bean.getObject();
	}

	@Bean
	public LoggingEventListener mappingEventsListener() {
		return new LoggingEventListener();
	}
}
