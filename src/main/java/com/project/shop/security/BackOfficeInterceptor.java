package com.project.shop.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BackOfficeInterceptor extends HandlerInterceptorAdapter {

       private Log log = LogFactory.getLog(this.getClass());
    
	   @Override
	   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	       if (log.isDebugEnabled()) {
	           log.info("======================================          START         ======================================");
	           log.info(" Request URI \t:  " + request.getRequestURI());
	       }
	       return super.preHandle(request, response, handler);
	   }
	    
	   @Override
	   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	       if (log.isDebugEnabled()) {
	           log.info("======================================           END          ======================================\n");
	       }
	   }
    
}
