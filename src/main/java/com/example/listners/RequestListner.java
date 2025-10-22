package com.example.listners;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import com.example.utils.DbPool;

import java.sql.Connection;

public class RequestListner implements ServletRequestListener {


    @Override
    public void requestInitialized(ServletRequestEvent sre) {

        DbPool dbThreadPool = (DbPool) sre.getServletContext().getAttribute("DbPool");

        ServletRequest request = sre.getServletRequest();

        try {

            request.setAttribute("conn", dbThreadPool.getConnection());

        } catch (InterruptedException e) {

            throw new RuntimeException(e);

        }

        System.out.println("Request initialized → Attribute 'startTime' set");

    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

        ServletRequest request = sre.getServletRequest();

        Connection conn = (Connection) request.getAttribute("conn");

        if ( conn != null) {

            DbPool dbThreadPool = (DbPool) sre.getServletContext().getAttribute("DbPool");

            dbThreadPool.release( conn );

        }
    }
}
