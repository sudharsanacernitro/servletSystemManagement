package com.example.servlets.repos;



import com.example.servlets.beans.SystemBean;
import com.example.utils.DbPool;

import java.sql.*;

import java.util.List;
import java.util.ArrayList;



public class SystemRepo  {


    private static SystemRepo singletonInstance = null;
    

   private SystemRepo() {

    }


    //singleton
    public static SystemRepo getInstance() {

        if(singletonInstance == null) {

            synchronized (SystemRepo.class) {

                if(singletonInstance == null) {

                    singletonInstance = new SystemRepo();

                }

            }

        }

        return singletonInstance;

    }


    public boolean addNewSystem(Connection conn ,  SystemBean newSystem ) {

        String sql = "INSERT INTO systemInfo(systemId, name, type, userId, ipAddress, macAddress) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, newSystem.getSystemId());
            ps.setString(2, newSystem.getName());


            ps.setString(3, newSystem.getType());

            ps.setInt(4, newSystem.getUserId());
            ps.setString(5, newSystem.getIpAddress());
            ps.setString(6, newSystem.getMacAddress());

           return ps.executeUpdate() == 1;



        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public SystemBean findById(Connection conn , int id ) {

        String query = "SELECT systemId , name , type , userId , ipAddress , macAddress FROM systemInfo WHERE systemId = ?";

        try (PreparedStatement ps = conn.prepareStatement(query) ) {

            ps.setInt( 1 , id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    SystemBean system = new SystemBean(
                            rs.getInt("systemId"),
                            rs.getString("name"),
                            rs.getString("type"),
                            rs.getInt("userId"),
                            rs.getString("ipAddress"),
                            rs.getString("macAddress")
                    );
                    return system;
                }
            }

        } catch( SQLException e ) {

                e.printStackTrace();
                return null;

        }

        return null;
    }


    public boolean updateRecord( Connection conn , SystemBean updateRecord , int systemId ) {

        String query = "UPDATE systemInfo SET name = ? , type = ? , userId = ? , ipAddress = ? , macAddress = ? WHERE systemId = ?";

        try( PreparedStatement ps = conn.prepareStatement(query) ){

            ps.setString( 1 , updateRecord.getName());
            ps.setString( 2 , updateRecord.getType());
            ps.setInt( 3 , updateRecord.getUserId());
            ps.setString( 4 , updateRecord.getIpAddress());
            ps.setString( 5 , updateRecord.getMacAddress());

            ps.setInt(6 , systemId );

            return ps.executeUpdate() == 1 ;

        } catch ( SQLException e) {

            System.out.println("can't able to update the record");
            return false;

        }

    }


    public boolean deleteRecord( Connection conn , int systemId ) {

        String query = " DELETE FROM systemInfo WHERE systemId = ?";

        try ( PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt( 1 , systemId );

            return ps.executeUpdate() == 1 ;

        } catch(SQLException e) {

            System.out.println("Can't able to delete the record");
            return false;
        }

    }


    public List<SystemBean> getAllSystem( Connection conn ) {

        String query = "SELECT * FROM systemInfo";

        try( PreparedStatement ps = conn.prepareStatement(query) ) {


                ResultSet rs = ps.executeQuery();

                List<SystemBean> resultArray = new ArrayList<>();

                while(rs.next()) {

                    SystemBean currSystem = new SystemBean(
                            rs.getInt("systemId"),
                            rs.getString("name"),
                            rs.getString("type"),
                            rs.getInt("userId"),
                            rs.getString("ipAddress"),
                            rs.getString("macAddress")
                    );

                    resultArray.add( currSystem );

                }

                rs.close();
                return resultArray;

        } catch( SQLException e) {

            return null;
        }

    }




}
