package com.example.servlets.controllers.system;

import com.example.servlets.services.SystemService;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class SearchSystems extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req , HttpServletResponse res) throws IOException {

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        Connection conn = (Connection) req.getAttribute("conn");

        String keyword = req.getParameter("keyword");

        JSONObject output = ( (SystemService)getServletContext().getAttribute("systemService") ).search( conn , keyword );

        output.put("status",200);
        output.put("message" , "results fetched successfully");


        res.getWriter().write( output.toString() );


    }
}
