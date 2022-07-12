//package com.xjtu.dbc.robserver.common.base;
//
//import com.xjtu.dbc.robserver.common.CommonService;
//
//import javax.annotation.Resource;
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.LinkedList;
//import java.util.List;
//
//
//@WebFilter("/*")
//public class SecurityFilter implements Filter {
//
//    public static List<String> ALWAYS_ALLOWED_PATHS = new LinkedList<>();
//
//    static {
//        ALWAYS_ALLOWED_PATHS.add("/login"); ALWAYS_ALLOWED_PATHS.add("/register"); ALWAYS_ALLOWED_PATHS.add("/forget");
//    }
//
//    @Resource
//    private CommonService commonService;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        String path = request.getServletPath();
//
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
