package com.tjoeun.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringConfigClass extends AbstractAnnotationConfigDispatcherServletInitializer{

	// Spring MVC project 에서 사용하는 Bean 들 정의하는 클래스 지정
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootAppContext.class};
	}

	// Spring MVC project 설정을 위한 클래스 지정
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {ServletAppContext.class};
	}

	// DispatcherServlet 에 mapping 할 url pattern 지정
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	// 한글 encoding filter 설정하기
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		return new Filter[] {encodingFilter};
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		super.customizeRegistration(registration);
		MultipartConfigElement config1 = 
				new MultipartConfigElement(null, 50*1024*1024, 50*1024*1024, 0);
		registration.setMultipartConfig(config1);
	}
		
	
	
}





