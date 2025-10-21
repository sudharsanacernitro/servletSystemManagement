package com.example.servlets.controllers.system;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.servlets.beans.SystemBean;
import com.example.utils.DbPool;
import com.example.utils.ParsesJson;

import com.example.servlets.services.SystemService;

import java.io.IOException;
import java.sql.Connection;

import org.json.JSONObject;

public class addNewSystem extends HttpServlet {

    private DbPool dbPool;


    @Override
    public void init() {

        dbPool = (DbPool) getServletContext().getAttribute("DbPool");

    }

    @Override
    public void doPost(HttpServletRequest req , HttpServletResponse res) throws IOException {

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");


        try( DbPool.PooledConnection  connWrapper =  dbPool.getPooledConnection()) {

            Connection conn = connWrapper.getConnection();

            JSONObject input = ParsesJson.getBody( req );

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


            JSONObject output = ( (SystemService) getServletContext().getAttribute("systemService") ).addNewSystem( conn , system );


            res.getWriter().write( output.toString());

            throw new InterruptedException("i");


        } catch ( InterruptedException e) {

            res.sendError( 400 , " Interrupted Exception "+e);

        } catch (IOException e) {



        } catch (IllegalArgumentException e) {



        }





    }




}
