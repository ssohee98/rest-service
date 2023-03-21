package com.example.restservice.user;

import com.example.restservice.helloworld.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        //@RestController 어노테이션으로 알아서 JSON 형태로 리턴
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        return service.findOne(id);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        //JSON 형태를 자바 객체 형태로 바꿔주기 위해 @RequestBody
        User savedUser = service.saveUser(user);
    }
}
