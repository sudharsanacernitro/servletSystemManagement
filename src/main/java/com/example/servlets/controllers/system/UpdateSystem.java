package com.example.servlets.controllers.system;

import com.example.servlets.beans.SystemBean;
import com.example.servlets.services.SystemService;
import com.example.utils.ParsesJson;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class UpdateSystem extends HttpServlet {



    @Override
    public void doPut(HttpServletRequest req , HttpServletResponse res) throws IOException {

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        JSONObject input = ParsesJson.getBody( req );

        Connection conn = (Connection) req.getAttribute("conn");


        if( !input.has("systemName") || !input.has("systemId") || !input.has("systemType")
                || !input.has("userId") || !input.has("systemIp") || !input.has("systemMac")
        ) {

            throw new IllegalArgumentException();

        }


        SystemBean system = new SystemBean();

        system.setSystemId( input.getInt("systemId") );
        system.setName( input.getString("systemName") );
        system.setType( input.getString("systemType") );
        system.setUserId( input.getInt("userId") );
        system.setIpAddress( input.getString("systemIp") );
        system.setMacAddress( input.getString("systemMac") );




        JSONObject output = ( (SystemService)getServletContext().getAttribute("systemService") ).updateSystem( conn , input.getInt("systemId") , system );

        res.getWriter().write( output.toString() );


    }
}
