package com.socialmedia.socialapp.DbEntity.User;

import com.socialmedia.socialapp.DbEntity.Follow.Follow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // get user by username
    @GetMapping("/getUser")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username){
        return userService.getUserByUsername(username);
    }

// UPDATE
// DELETE

}
