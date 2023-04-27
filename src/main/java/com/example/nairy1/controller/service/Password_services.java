package com.example.nairy1.controller.service;

import com.example.nairy1.controller.User.Password;
import com.example.nairy1.controller.databaseConnection.DBUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class Password_services {

    Connection connection;
    public Password_services() throws SQLException{
        connection = DBUtil.getConnection();
    }

    public String addPasswords(int userid,String name ,String password, String url){
//        List<Password> pass = new ArrayList<>();
        String pass = null;
        try {
            PreparedStatement getStmt = connection.prepareStatement("select count(*) as cnt from passwords where url = ? and userid = ?;");
            getStmt.setString(1,url);
            getStmt.setInt(2,userid);
            ResultSet rs = getStmt.executeQuery();
            int i=0;
            while(rs.next()){
                i=rs.getInt(1);
            }
            if(i==0){
                PreparedStatement Stmt = connection.prepareStatement("insert into passwords(userid,name,password,url) values(?,?,?,?);");
                Stmt.setInt(1,userid);
                Stmt.setString(2,name);
                Stmt.setString(3,password);
                Stmt.setString(4,url);
                Stmt.executeUpdate();
                pass = password;


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pass;
    }

    public List<Password> fetchPassword(int userid,String name,String password,String url){
        List<Password> list = new ArrayList<>();
        try{
            PreparedStatement getStmt = connection.prepareStatement("select * from passwords where userid = ?;");
            getStmt.setInt(1,userid);

            ResultSet rs = getStmt.executeQuery();
            while(rs.next()){
                Password p = new Password();
                p.setUserid(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPassword(rs.getString(3));
                p.setUrl(rs.getString(4));
                list.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public String delete(int userid,String name,String password,String url){
        String ans = "not deleted";
        try{
            PreparedStatement getStmt = connection.prepareStatement("delete from passwords where userid = ? and url=?;");
            getStmt.setInt(1,userid);
            getStmt.setString(2,url);
//            System.out.println("hi"+userid+"hello"+url);
            getStmt.executeUpdate();
            ans = "deleted";

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ans;
    }

}
