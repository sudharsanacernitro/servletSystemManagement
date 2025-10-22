package com.example.servlets.controllers.system;

import com.example.servlets.services.SystemService;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class GetAllSystem extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req , HttpServletResponse res) throws IOException {

        Connection conn = (Connection) req.getAttribute("conn");

        JSONObject output = ( (SystemService)getServletContext().getAttribute("systemService") ).getAllSystems( conn );

        res.getWriter().write( output.toString() );

    }
}
