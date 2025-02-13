package com.socialmedia.socialapp.DbEntity.User.DTO.Profile;

import com.socialmedia.socialapp.DbEntity.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PutMapping
    public ResponseEntity<User> updateProfile(@RequestBody Profile profile) {
        System.out.println(profile);
        User updatedUser = profileService.updateProfile(profile);
        return ResponseEntity.ok(updatedUser);
    }
}
