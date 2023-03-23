package com.example.restservice.user;

import com.example.restservice.helloworld.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = service.findUser(id);

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        //user와 link를 함께 리턴
        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).retrieveAllUsers()).withRel("all-users"));
    }

    @PostMapping("/users")
    public void createUser(@Valid @RequestBody User user) {
        //JSON 형태를 자바 객체 형태로 바꿔주기 위해 @RequestBody
        service.saveUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.findUser(id);

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        } service.deleteUser(id);
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPostByUser(@PathVariable int id) {
        User user = service.findUser(id);

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        return service.findAllPost(id);
    }

    @PostMapping("/users/{id}/posts")
    public void createPosts(@PathVariable int id, @RequestBody Post post) {
        User user = service.findUser(id);

        if(user == null) {  //user 객체가 존재하는지 아닌지
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        post.setUser_id(id);
        service.savePost(post);
    }
}
