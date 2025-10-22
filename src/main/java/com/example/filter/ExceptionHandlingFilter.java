package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/system/*")
public class ExceptionHandlingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();

            HttpServletResponse httpResp = (HttpServletResponse) response;

            httpResp.setStatus(500);
            httpResp.setContentType("application/json");
            httpResp.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");

        }
    }

    @Override
    public void destroy() {

    }
}
