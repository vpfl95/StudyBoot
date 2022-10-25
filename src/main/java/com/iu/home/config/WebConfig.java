package com.iu.home.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration	//객체생성 후 실행 레거시의 servlet-context 처럼 ***-context.xml
@Slf4j	//로그기록 찍기 Logger
public class WebConfig implements WebMvcConfigurer{
	
	@Value("${app.upload.base}")
	private String filePath;
	
	@Value("${app.url.path}")
	private String urlPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		log.info("============================");
		log.info("filePath {}", filePath);	// file:///result/upload/
		log.info("urlPath {}", urlPath);	// /file/**
		log.info("============================");
		
		//<resources mapping="/resources/**" location="/resources/" />
		registry.addResourceHandler(urlPath)	//요청 url주소 매핑
				.addResourceLocations(filePath);
	}
}
