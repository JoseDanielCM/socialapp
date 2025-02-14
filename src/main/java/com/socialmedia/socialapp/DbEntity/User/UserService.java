package com.socialmedia.socialapp.DbEntity.User;

import com.socialmedia.socialapp.DbEntity.Follow.Follow;
import com.socialmedia.socialapp.DbEntity.Follow.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FollowRepository followRepository;

    // GET FOLLOWERS


    public ResponseEntity<User> getUser(Long id) {
        return userRepository.findById(id)
               .map(user -> ResponseEntity.ok().body(user))
               .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username)
               .map(user -> ResponseEntity.ok().body(user))
               .orElse(ResponseEntity.notFound().build());
    }

    public List<User> getFollowersByUsername(String username) {
        System.out.println(username);
        return followRepository.findFollowersByUsername(username);
    }

    public List<User> getFollowsByUsername(String username) {
        System.out.println(username);
        return followRepository.findFollowsByUsername(username);
    }

    public List<User> searchUsersByUsername(String username) {
        System.out.println(username);
        // Aquí puedes agregar lógica adicional si es necesario
        return userRepository.findByUsernameContainingIgnoreCase(username);  // Busca por nombre de usuario
    }
}
