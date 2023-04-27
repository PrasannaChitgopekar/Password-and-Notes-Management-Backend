package com.example.nairy1.controller.service;

import com.example.nairy1.controller.User.User;
import com.example.nairy1.controller.databaseConnection.DBUtil;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Repository
public class User_service {

    Connection connection;
    public User_service() throws SQLException{
        connection = DBUtil.getConnection();
    }
//    @Override
    public boolean setUser(String uemail, String password){

        try
        {
            int strength = 10; // work factor of bcrypt
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
            String encodedPassword = bCryptPasswordEncoder.encode(password);
            PreparedStatement getStmt = connection.prepareStatement("select count(*) as cnt from user where     email = ?");
            getStmt.setString(1,uemail);
            ResultSet rs = getStmt.executeQuery();
            int i = 0;
            while(rs.next()){
                i = rs.getInt(1);
            }
            if(i == 0){
                PreparedStatement stmt = connection.prepareStatement("insert into user(email,password) values(?,?);");

                stmt.setString(1,uemail);
                stmt.setString(2,encodedPassword);
                stmt.executeUpdate();
                return true;
            }


        }catch (SQLException e){
//            e.printStackTrace();
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;

    }


    //login
    public List<User> login(String uemail, String password){
        List<User> ans = new ArrayList<>();
        String hash_pass =null;
        try{
            PreparedStatement getStmt1 = connection.prepareStatement("select * from user where email =?;");
            getStmt1.setString(1,uemail);
            ResultSet rs1 = getStmt1.executeQuery();
            while(rs1.next()){
                hash_pass = rs1.getString(3);
            }
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
            boolean matches = encoder.matches(password, hash_pass);
            if(matches) {
                PreparedStatement getStmt2 = connection.prepareStatement("select * from user where email = ? and password=? ;");
                getStmt2.setString(1, uemail);
                getStmt2.setString(2, hash_pass);
                ResultSet rs2 = getStmt2.executeQuery();
                while(rs2.next()){
                    User u = new User();
                    u.setUserid(rs2.getInt(1));
                    u.setUemail(rs2.getString(2));
                    ans.add(u);
                }
                System.out.println(ans);
//                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ans;
    }
}
