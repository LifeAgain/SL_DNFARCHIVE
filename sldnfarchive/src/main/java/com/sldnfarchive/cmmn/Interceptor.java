package com.sldnfarchive.cmmn;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.HandlerInterceptor;

public class Interceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		HttpSession session = req.getSession(false);
		String reqUrl = req.getRequestURL().toString();
		
		System.out.println("============================");
		System.out.println("preHandle >>>  Controller 실행 전 실행");
		System.out.println("============================");
		
		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
		res.setHeader("Pragma", "no-cache"); // HTTP 1.0
		res.setDateHeader("Expires", 0); // Proxies
		
		if(session == null) {
			if(reqUrl.contains("/login")) {
				return true;
			} else {
				res.sendRedirect(req.getContextPath() + "/");
				return false;
			}
		} else {
			Integer idx = (Integer) session.getAttribute("userIdx");
			
			System.out.println("jsessionid:" + session.getId() + ", idx: " + idx);
			
			if(idx == null) {
				if(reqUrl.contains("/login")) {
					return true;
				} else {
					res.sendRedirect(req.getContextPath() + "/");
					return false;
				}
			} else {
				if(reqUrl.contains("/login") && !reqUrl.contains("/login/logout.do")) {
					res.sendRedirect(req.getContextPath() + "/main/main.do");
					return false;
				} else return true;
			}
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView mv) throws Exception {
		System.out.println("============================");
		System.out.println("postHandle >>>  Controller 실행 후 실행");
		System.out.println("============================");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex) throws Exception {
		System.out.println("============================");
		System.out.println("afterCompletion >>>  preHandle 메소드 return값이 true일 때 실행");
		System.out.println("============================");
	}
}
