package com.example.nairy1.controller.service;

import com.example.nairy1.controller.User.Notes;
import com.example.nairy1.controller.databaseConnection.DBUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class Notes_services {
    Connection connection;
    public Notes_services() throws SQLException {
        connection = DBUtil.getConnection();
    }

    public boolean addNote(int userid,String title,String tag,String description){
        boolean ans = false;
        try{
            PreparedStatement getStmt1 = connection.prepareStatement("select * from notes where userid=? and title=?;");
            getStmt1.setInt(1,userid);
            getStmt1.setString(2,title);
            ResultSet rs= getStmt1.executeQuery();
            int i=0;
            while(rs.next()){
                i = rs.getInt(1);
            }
            if(i==0) {
                PreparedStatement getStmt = connection.prepareStatement("insert into notes(userid,title,tag,description) values(?,?,?,?);");
                getStmt.setInt(1, userid);
                getStmt.setString(2, title);
                getStmt.setString(3, tag);
                getStmt.setString(4, description);
                getStmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Notes> fetchNotes(int userid, String title, String tag, String description){
        List<Notes> list = new ArrayList<>();
        try{
            PreparedStatement Stmt = connection.prepareStatement("select * from notes where userid=? ;");
            Stmt.setInt(1,userid);
            ResultSet rs = Stmt.executeQuery();
            while(rs.next()){
                Notes N = new Notes();
                N.setUserid(rs.getInt(1));
                N.setTitle(rs.getString(2));
                N.setTag(rs.getString(3));
                N.setDescription(rs.getString(4));
                list.add(N);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public String deleteNote(int userid,String title,String tag,String description){
        String ans ="not deleted";
        try {
            PreparedStatement getStmt = connection.prepareStatement("delete from notes where userid = ? and title=?;");
            getStmt.setInt(1,userid);
            getStmt.setString(2,title);
            getStmt.executeUpdate();
            ans = "note deleted successfully";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ans;
    }


}
