package com.example.servlets.beans;

import java.io.Serializable;


public class SystemBean implements Serializable {

    private static final long serialVersionUID = 1L;  // fixed ID

    private int systemId;
    private String name;
    private String type;
    private int userId = -1;
    private String ipAddress;
    private String macAddress;

    public SystemBean() {}

    public SystemBean(int systemId, String name, String type, int userId, String ipAddress, String macAddress) {
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
        this.name = name;
        this.systemId = systemId;
        this.type = type;
        this.userId = userId;
    }





    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SystemBean{" +
                "ipAddress='" + ipAddress + '\'' +
                ", systemId=" + systemId +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", userId=" + userId +
                ", macAddress='" + macAddress + '\'' +
                '}';
    }

}
