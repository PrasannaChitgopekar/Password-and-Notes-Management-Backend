package com.example.nairy1.controller.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id

    @Column(name = "userid")
    private int userid;

    @Column(name = "email")
    private String uemail;

    @Column(name = "password")
    private String upassword;



    public User() {
        super();
    }
    public User(int userid,String uemail, String upassword) {
        this.userid=userid;
        this.uemail = uemail;
        this.upassword = upassword;
    }

    public int getuserid() {
        return userid;
    }

    public void setUserid(int userid){this.userid=userid;}
    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }
    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }
}