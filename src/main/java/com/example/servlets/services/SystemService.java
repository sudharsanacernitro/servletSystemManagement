package com.example.servlets.services;

import com.example.servlets.beans.SystemBean;
import com.example.servlets.repos.SystemRepo;
import com.example.utils.DbPool;
import org.json.JSONObject;

import java.sql.Connection;

public class SystemService {

    private final SystemRepo systemRepo;

    private static SystemService INSTANCE;

    private SystemService(SystemRepo systemRepo) {

        this.systemRepo = systemRepo;

    }

    public static SystemService getInstance( SystemRepo systemRepo) {

        if( INSTANCE == null ) {

            synchronized ( SystemService.class ) {

                if(INSTANCE == null ) {

                    INSTANCE = new SystemService( systemRepo );

                }

            }

        }

        return INSTANCE;

    }


    public JSONObject addNewSystem( Connection conn ,  SystemBean newSystem ) {

        JSONObject res = new JSONObject();

        if( systemRepo.findById( conn , newSystem.getSystemId()) == null ){

            systemRepo.addNewSystem( conn , newSystem );

            res.put("status" , 200);
            res.put("message" , "System added successfully");

        }

        else {

            res.put("status" , 400);
            res.put("message" , "System already exsists");

        }

        return res;

    }




}
