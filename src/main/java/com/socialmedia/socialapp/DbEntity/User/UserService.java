package com.socialmedia.socialapp.DbEntity.User;

import com.socialmedia.socialapp.DbEntity.Follow.Follow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    // GET FOLLOWERS
    public ResponseEntity<List<Follow>> getFollowers(Long id){
        System.out.println("here");
        return userRepository.findById(id)
               .map(user -> ResponseEntity.ok().body(user.getFollowers()))
               .orElse(ResponseEntity.notFound().build());

        /*
        implement the exceptions
        User user = userRepository.findById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return ResponseEntity.ok(user.getFollowers());
         */
    };

    public ResponseEntity<User> getUser(Long id) {
        return userRepository.findById(id)
               .map(user -> ResponseEntity.ok().body(user))
               .orElse(ResponseEntity.notFound().build());


        /*
        implement the exceptions
        User user = userRepository.findById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
                return ResponseEntity.ok(user);
         */
    }
}
