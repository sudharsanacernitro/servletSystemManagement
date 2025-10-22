package com.example.servlets.controllers.system;


import com.example.servlets.services.SystemService;
import com.example.utils.DbPool;
import com.example.utils.ParsesJson;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class DeleteSystem extends HttpServlet {




    @Override
    public void doGet(HttpServletRequest req , HttpServletResponse res) throws IOException , ServletException {

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");


        Connection conn = (Connection) req.getAttribute("conn");


        JSONObject input = ParsesJson.getBody( req );

        if( !input.has("systemId")) throw new IllegalArgumentException();

        int systemId = input.getInt("systemId");

        JSONObject output = ( (SystemService)getServletContext().getAttribute("systemService") ).deleteSystem( conn , systemId );

        res.getWriter().write(output.toString());

    }

}
