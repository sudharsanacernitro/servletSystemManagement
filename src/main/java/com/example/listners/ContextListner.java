package com.example.listners;

import com.example.servlets.repos.SystemRepo;
import com.example.servlets.services.SystemService;
import com.example.utils.DbPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;

public class ContextListner implements ServletContextListener {

    private  DbPool threadPool;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {

            threadPool = DbPool.getInstance();

        } catch (SQLException  | ClassNotFoundException e) {

            throw new RuntimeException(e);

        }


        // WIRING

        //System

            //repository
            SystemRepo systemRepoInstance = SystemRepo.getInstance();

            //service
            SystemService systemServiceInstance = SystemService.getInstance( systemRepoInstance );



        sce.getServletContext().setAttribute("DbPool" , threadPool);
        sce.getServletContext().setAttribute("systemService" , systemServiceInstance);

    }

    @Override
    public void contextDestroyed( ServletContextEvent sce ) {

        threadPool.closeAll();

    }

}
