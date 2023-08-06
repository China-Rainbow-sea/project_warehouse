package com.rainbowsea.mhl.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //System.out.println("执行了");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 设置编码
        //request.setCharacterEncoding("utf-8");
        //response.setCharacterEncoding("utf-8");
        //response.setContentType("text/html;charset=utf-8");

        filterChain.doFilter(servletRequest,response);  // 跳过去。

    }

    @Override
    public void destroy() {

    }
}
