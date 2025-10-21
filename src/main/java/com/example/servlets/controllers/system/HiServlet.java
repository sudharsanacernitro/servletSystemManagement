package com.example.servlets.controllers.system;


import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HiServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException , ServletException {

        response.setContentType("text/html");

        response.setStatus(200);


        RequestDispatcher rd = request.getRequestDispatcher("/hello");

//        rd.forward(request , response);

        response.getWriter().println("<h1>"+ request.getRequestURL()+"</h1><br><h1>"+ (String)getServletContext().getAttribute("object") +"</h1>");

    }

}
