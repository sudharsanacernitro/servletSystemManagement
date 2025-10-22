package com.example.servlets.services;

import com.example.servlets.beans.SystemBean;
import com.example.servlets.repos.SystemRepo;
import com.example.utils.DbPool;
import org.json.JSONObject;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public JSONObject deleteSystem( Connection conn , int systemId ) {

        JSONObject res = new JSONObject();

        if( systemRepo.findById( conn , systemId ) != null ){

            if ( systemRepo.deleteRecord( conn , systemId ) ) {

                res.put( "status" , 200 );
                res.put( "message" , "system is removed from DB" );


                return res;

            }

        }

        res.put( "status" , 500 );
        res.put("message" , "can't able to delete");

        return res;

    }


    public JSONObject updateSystem( Connection conn , int systemId , SystemBean system ) {

        JSONObject res = new JSONObject();

        if( systemRepo.findById( conn , systemId ) != null ) {

            if(systemRepo.updateRecord( conn , system , systemId )) {

                res.put("status" , 200);
                res.put("message" , "Record updated successfully");

                return res;

            }

        } else {


            if( systemRepo.addNewSystem( conn , system) ) {

                res.put("status" , 200);
                res.put("message" , "No record found , successfully created new data");

                return res;

            }

        }

        res.put("status" , "500");
        res.put("message" , "Internal Server Error");

        return res;

    }


    public JSONObject getAllSystems( Connection conn) {

        List<SystemBean> systemsList = systemRepo.getAllSystem( conn );

        List<JSONObject> systems = systemsList.stream()
                .map( (system) -> {
                    JSONObject jsonSystem = new JSONObject();

                    jsonSystem.put("systemId" , system.getSystemId() );
                    jsonSystem.put("systemName" , system.getName() );
                    jsonSystem.put("systemType" , system.getType() );
                    jsonSystem.put("userId" , system.getUserId() );
                    jsonSystem.put("systemIp" , system.getIpAddress() );
                    jsonSystem.put("systemMac" , system.getMacAddress() );

                    return jsonSystem;
                })
                .collect(Collectors.toList());

        JSONObject res = new JSONObject();

        res.put( "status" , 200 );
        res.put( "message" , "list of systems");

        res.put("systems" , systems);

        return res;


    }

    public JSONObject search(Connection conn ,  String keyword ) {

        List<SystemBean> systems = systemRepo.getAllSystem( conn );

        List<JSONObject> systemList = systems.stream()
                .filter(system -> (system.getSystemId()+"" ).equals( keyword )
                        || system.getName().equals( keyword )
                        || system.getType().equals( keyword )
                        || (system.getUserId()+"").equals( keyword )
                        || system.getIpAddress().equals( keyword )
                        || system.getMacAddress().equals( keyword )
                ).map( (system) -> {
                    JSONObject jsonSystem = new JSONObject();

                    jsonSystem.put("systemId" , system.getSystemId() );
                    jsonSystem.put("systemName" , system.getName() );
                    jsonSystem.put("systemType" , system.getType() );
                    jsonSystem.put("userId" , system.getUserId() );
                    jsonSystem.put("systemIp" , system.getIpAddress() );
                    jsonSystem.put("systemMac" , system.getMacAddress() );

                    return jsonSystem;
                })
                .collect(Collectors.toList());

        JSONObject result = new JSONObject();

        result.put("results" , systemList );

        return result;


    }




}
