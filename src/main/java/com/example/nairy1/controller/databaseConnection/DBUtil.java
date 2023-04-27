package com.example.nairy1.controller.databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static Connection connection = null;
    public static Connection getConnection(){
        if(connection!=null){
            return connection;
        }
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/java?useSSL=false";
        String user = "root";
        String password = "PrasannaPC2002";
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

            return connection;
    }
}