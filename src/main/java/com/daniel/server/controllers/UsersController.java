package com.daniel.server.controllers;

import com.daniel.server.beans.Company;
import com.daniel.server.beans.User;
import com.daniel.server.dto.SuccessfulLoginData;
import com.daniel.server.dto.UserLoginData;
import com.daniel.server.entities.UserEntity;
import com.daniel.server.enums.UserType;
import com.daniel.server.exceptions.ServerException;
import com.daniel.server.logic.UserLogic;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UserLogic userLogic;

    @Autowired
    public UsersController(UserLogic userLogic){
        this.userLogic = userLogic;
    }

    @PostMapping
    public void createUser (@RequestBody UserEntity user) throws ServerException {
        user.setUserType(UserType.CUSTOMER);
        userLogic.createUser(user);
    }

    //can I change it ? 1 minute I'll just backup everything ok
    @PostMapping("/login")//here is the problem, but it was working I didn't change any of it. I was changing the serverExceptions and some service and dao data
    public String login (@RequestBody UserLoginData userLoginData) throws ServerException, JsonProcessingException {
        String token = userLogic.login(userLoginData);
        return token;
    }

    @PutMapping
    public void updateUser (@RequestBody UserEntity user) throws ServerException {
        userLogic.updateUser(user);
    }

    @DeleteMapping("{userId}")
    public void removeUser (@PathVariable ("userId") long id) throws ServerException {
        userLogic.removeUser(id);
    }

    @GetMapping("{userId}")
    public UserEntity getUser(@PathVariable("userId") long id) throws ServerException {
        UserEntity user = userLogic.getUser(id);
        return user;
    }

    @GetMapping
    public List<UserEntity> getAllUsers (@RequestParam("page") int page) throws ServerException {
        List<UserEntity> users = userLogic.getAllUsers(page);
        return users;
    }

}
