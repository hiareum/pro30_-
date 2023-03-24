package com.myspring.pro30.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ViewNamelnterceptor extends HandlerInterceptorAdapter {
	
	//HttpServletRequest를 통해 현재요청에대한 정보를 얻어올 수 있다 getViewName메서드를 호출하여 현재요청에대한 view의 이름을 추출하고
	//request.setAttribute메서드를 사용하여 request에 해당 view이름을 저장합니다
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		try {
			String viewName = getViewName(request);
			request.setAttribute("viewName", viewName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	//컨트롤러가 요청을 처리한 후 view가 생성되기 전에 호출됨, ModelAndView를 통해 컨트롤러에서 전달된 데이터와 view의 이름을 얻어올 수 있음
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	//view가 생성된 후 response가 전송되기 전에 호출됨 
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	//HttpServletRequest를 통해 현재 요청에 대한 정보를 추출하여 view의 이름을 반환하는 메서드 getContextPath,getRequestURI
	//등의 정보를 이용하여 view의 이름을 추출
	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String fileName = uri.substring(begin, end);
		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		if (fileName.lastIndexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/", 1), fileName.length());
		}
		return fileName;
	}
}
