package com.example.nairy1.controller;


import com.example.nairy1.controller.User.User;
import com.example.nairy1.controller.service.User_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@RestController
@CrossOrigin
//@Controller
public class controller {

    @Autowired
    public User_service userservice;
    @PostMapping("/login")
    public List<User> login(@RequestBody User A){
        return this.userservice.login(A.getUemail(),A.getUpassword());
    }

    @PostMapping("/signup")
    public String signUP(@RequestBody User A)  {
        boolean a = this.userservice.setUser(A.getUemail(),A.getUpassword());
        System.out.println(a);
        if(a == true){
            return  "done33";
        }
        return "done";
    }
}
