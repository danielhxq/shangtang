package com.shangtang.controller;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
public class CxfConfig {

	public static void main(String... args) {
		SpringApplication.run(CxfConfig.class, args);
	}

	@Bean
	public ServletRegistrationBean dispatcherServlet() {
		return new ServletRegistrationBean(new CXFServlet(), "/soap/*");
	}

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}

	@Bean
	public FaceReportService faceReportService() {
		return new FaceReportServiceImpl();
	}

	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), faceReportService());
		endpoint.publish("/facereport");
		return endpoint;
	}

	@Bean
	public ServletRegistrationBean dispatcherRestServlet() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.scan("com.shangtang.controller");
		DispatcherServlet disp = new DispatcherServlet(context);
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(disp);
		registrationBean.setLoadOnStartup(1);
		registrationBean.addUrlMappings("/st/*");
		registrationBean.setName("rest");
		return registrationBean;
	}

}
