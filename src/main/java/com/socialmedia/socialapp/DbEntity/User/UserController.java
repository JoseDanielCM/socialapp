package com.socialmedia.socialapp.DbEntity.User;

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
        System.out.println(id);
        return ResponseEntity.ok(userService.getUser(id).getBody());
    }

    // get user by username
    @GetMapping("/getUser")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username){
        return ResponseEntity.ok(userService.getUserByUsername(username).getBody());
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUserInfo(@CookieValue(value = "token", defaultValue = "") String token) {
        if (token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no presente");
        }

        String username = jwtService.getUsernameFromToken(token);
        User user = userService.getUserByUsername(username).getBody();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/followers")
    public ResponseEntity<?> getFollowers(@CookieValue(value = "token", defaultValue = "") String token) {
        if (token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No token");
        }

        String username = jwtService.getUsernameFromToken(token);
        List<User> followers = userService.getFollowersByUsername(username);
        System.out.println(followers);
        return ResponseEntity.ok(followers);
    }

    @GetMapping("/follows")
    public ResponseEntity<?> getFollows(@CookieValue(value = "token", defaultValue = "") String token) {
        if (token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No token");
        }

        String username = jwtService.getUsernameFromToken(token);
        List<User> follows = userService.getFollowsByUsername(username);
        System.out.println(follows);
        return ResponseEntity.ok(follows);
    }

    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String username) {
        System.out.println(username);

        return userService.searchUsersByUsername(username);
    }
// UPDATE
// DELETE

}
