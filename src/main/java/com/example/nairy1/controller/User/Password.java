package com.example.nairy1.controller.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Password {

    @Id

    @Column(name = "userid")
    private int userid;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "url")
    private String url;


    public Password() {
        super();
    }
    public Password(int userid,String name, String password,String url) {
        this.userid=userid;
        this.name = name;
        this.password = password;
        this.url = url;
    }

    public int getuid() {
        return userid;
    }

    public void setUserid(int userid){this.userid=userid;}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}