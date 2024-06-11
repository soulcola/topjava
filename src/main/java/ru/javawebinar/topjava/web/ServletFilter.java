package ru.javawebinar.topjava.web;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        if(servletRequest.getParameter("action") == null){
//            ((HttpServletResponse) servletResponse).sendRedirect(((HttpServletRequest)servletRequest).getContextPath() + "/meals?action=list");
//            return;
//        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
