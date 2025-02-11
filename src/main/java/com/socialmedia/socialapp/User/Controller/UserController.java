package com.socialmedia.socialapp.User.Controller;

import com.socialmedia.socialapp.Follow.Entity.Follow;
import com.socialmedia.socialapp.User.Entity.User;
import com.socialmedia.socialapp.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    UserService userService;


// CREATE

// READ

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @GetMapping("/getFollowers/{id}")
    public ResponseEntity<List<Follow>> getFollowers(@PathVariable Long id){
        return userService.getFollowers(id);
    }

// UPDATE
// DELETE

}
