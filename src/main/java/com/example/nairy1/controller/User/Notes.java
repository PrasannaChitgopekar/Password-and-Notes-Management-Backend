package com.example.nairy1.controller.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Notes {
    @Id

    @Column(name = "userid")
    private int userid;

    @Column(name = "title")
    private String title;

    @Column(name = "tag")
    private String tag;

    @Column(name = "description")
    private String description;

    public Notes(){
        super();
    }

    public Notes(int userid,String title,String tag , String description){
        this.userid = userid;
        this.title = title;
        this.tag = tag;
        this.description = description;
    }


    public int getUserid(){
        return userid;
    }
    public void setUserid(int userid){
        this.userid= userid;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title= title;
    }
    public String getTag(){
        return tag;
    }
    public void setTag(String tag){
        this.tag= tag;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description= description;
    }
}
