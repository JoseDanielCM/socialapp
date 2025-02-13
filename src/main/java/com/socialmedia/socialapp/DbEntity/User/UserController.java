package com.socialmedia.socialapp.DbEntity.User;

import com.socialmedia.socialapp.DbEntity.Follow.Follow;
import com.socialmedia.socialapp.Jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

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

    @GetMapping("/me")
    public ResponseEntity<?> getUserInfo(@CookieValue(value = "token", defaultValue = "") String token) {
        if (token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no presente");
        }

        String username = jwtService.getUsernameFromToken(token);
        User user = userService.getUserByUsername(username).getBody();
        System.out.println(user);
        return ResponseEntity.ok(user);
    }


// UPDATE
// DELETE

}
