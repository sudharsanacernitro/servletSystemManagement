package com.example.servlets.beans;

import java.io.Serializable;

public class UserBean implements Serializable {

    private int userId;
    private String userName;
    private String location;

    public UserBean(int userId, String userName, String location) {

        this.location = location;
        this.userId = userId;
        this.userName = userName;

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
