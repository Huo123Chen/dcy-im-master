package com.dcy.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @Description: 拦截所有请求 进行token校验
* @return
**/
@WebFilter(filterName = "requestFilter", urlPatterns = { "/*" }, initParams = {
@WebInitParam(name = "enable", value = "true") })
public class RequestFilter implements Filter {
	
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
 
    }
 
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest)servletRequest;
 
        //String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", "*");
        //response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String method = request.getMethod();
        String authorization = response.getHeader("authorization ");

        //获取token

//        if(method.equalsIgnoreCase("upload")){
//            servletResponse.getOutputStream().write("Success".getBytes("utf-8"));
//        }else{
//            filterChain.doFilter(servletRequest, servletResponse);
//        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
 
    @Override
    public void destroy() {
 
    }


	

}
