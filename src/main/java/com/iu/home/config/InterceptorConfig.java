package com.iu.home.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.iu.home.interceptors.StudyInterceptor;
import com.iu.home.interceptors.TestInterceptor;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	private TestInterceptor testInterceptor;
	
	@Autowired
	private StudyInterceptor studyInterceptor;
	
	@Override
		public void addInterceptors(InterceptorRegistry registry) {
			//method chaining
			// 적용할 인터셉터 등록
			registry.addInterceptor(testInterceptor)
					.addPathPatterns("/qna/**")			//인터셉터를 적용할 url등록
					.addPathPatterns("/notice/**")
					.excludePathPatterns("/qna/detail")	//인터셉터를 제외할 url 등록
					.excludePathPatterns("/qna/write");
			
			// 추가 인터셉터 등록
			registry.addInterceptor(studyInterceptor)
					.addPathPatterns("/qna/**");
			
			
			
			//WebMvcConfigurer.super.addInterceptors(registry);
		}
	
}
