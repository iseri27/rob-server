package com.xjtu.dbc.robserver.common.base;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.Utils;
import com.xjtu.dbc.robserver.manage.module.ManageModuleService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@WebFilter("/*")
public class SecurityFilter implements Filter {

    public static List<String> ALWAYS_ALLOWED_PATHS = new LinkedList<>();

    static {
        ALWAYS_ALLOWED_PATHS.add("/login"); ALWAYS_ALLOWED_PATHS.add("/register"); ALWAYS_ALLOWED_PATHS.add("/forget");
    }

    @Resource
    private CommonService commonService;

    @Resource
    private ManageModuleService manageModuleService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getServletPath();

        Integer moduleId = manageModuleService.getModuleIdByPath(path);
        log.info("Module ID : " + moduleId);

        if (moduleId == null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // 检查该模块是否可用
            Boolean available = manageModuleService.moduleAvailable(path);

            if (available) {
                log.info("该模块可用.");
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                Utils.outJson(response, Result.fail(Result.ERR_CODE_BUSINESS, "维修中, 功能未开放!"));
            }
        }
    }

    @Override
    public void destroy() {

    }
}
