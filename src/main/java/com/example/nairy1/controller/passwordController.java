package com.example.nairy1.controller;



import com.example.nairy1.controller.User.Password;
import com.example.nairy1.controller.service.Password_services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;


@RestController
@CrossOrigin


public class passwordController {
    @Autowired
    public Password_services password_services;

    @PostMapping("/password/fetchPassword")
    public List<Password> fetchPasswords(@RequestBody Password U){
        return this.password_services.fetchPassword(U.getuid(),U.getName(),U.getPassword(),U.getUrl());
    }

    @PostMapping("/password/addPasswords")
    public String setPassword(@RequestBody Password P){
//        System.out.println(P.getuid());
        return this.password_services.addPasswords(P.getuid(),P.getName(),P.getPassword(),P.getUrl());
    }

    @PostMapping("password/delete")
    public String delete(@RequestBody Password X){
        System.out.println(X.getUrl());
        return this.password_services.delete(X.getuid(),X.getName(),X.getPassword(),X.getUrl());
    }



}
