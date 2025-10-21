package com.example.servlets.repos;

import com.example.servlets.beans.UserBean;

import java.util.HashMap;

public class UserRepo {

    HashMap< Integer , UserBean> userDB ;

    static UserRepo singletonInstance = null ;


    private UserRepo() {

    }


    //To maintain single instance
    public static UserRepo createInstance() {

        if(singletonInstance == null) {

            singletonInstance = new UserRepo();

            singletonInstance.loadUserData();
        }

        return singletonInstance;

    }


    void loadUserData() {

        userDB = new HashMap<>();


        userDB.put(1,new UserBean( 1 , "Ram" , "Z4MC48"));

        userDB.put(2,new UserBean(2,"Sudharsan" , "Z4MC41"));

        userDB.put(3,new UserBean(3,"Anbu" , "Z4MC42"));


    }



    public UserBean findById(int id) {


            if( !userDB.containsKey(id) )  return null;

            return userDB.get(id);


    }


}
