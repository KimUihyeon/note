package com.springtest.ijs.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        // 초기화 될때 실행됨

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FillterWapper myFillter = new FillterWapper((HttpServletRequest)servletRequest);

        myFillter.setParameter("test","1");
        myFillter.setParameter("test2","2");

        filterChain.doFilter(myFillter, servletResponse);
    }

    public void destroy() {
        // 죽을때 실행됨

    }
}
