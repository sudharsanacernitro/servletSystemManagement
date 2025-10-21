package com.example.filter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;



public class AuthFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {}

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;


        request.setAttribute("filter","Applied");

        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        // Allow login page and static files without authentication
        if (uri.endsWith("login.jsp") || uri.endsWith("/login") || uri.contains("/assets/")) {
            chain.doFilter(req, res);
            return;
        }


            chain.doFilter(req, res);

    }

    public void destroy() {}
}