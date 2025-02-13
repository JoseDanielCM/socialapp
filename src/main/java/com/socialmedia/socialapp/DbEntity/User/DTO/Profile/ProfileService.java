package com.socialmedia.socialapp.DbEntity.User.DTO.Profile;

import com.socialmedia.socialapp.DbEntity.User.User;
import com.socialmedia.socialapp.DbEntity.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.color.ProfileDataException;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private UserRepository userRepository;

    public User updateProfile(Profile profile) {
        // Supongamos que obtienes el ID del usuario de algún lugar, como el token JWT
        Long userId = profile.getUserId();

        Optional<User> existingUser = userRepository.findById(userId);

        if (existingUser.isPresent()) {
            User user = existingUser.get();

            // Solo actualiza los campos que vinieron en la solicitud
            if (profile.getName() != null) {
                user.setFirst_name(profile.getName());
            }
            if (profile.getLastname() != null) {
                user.setLast_name(profile.getLastname());
            }
            if (profile.getBio() != null) {
                user.setBio(profile.getBio());
            }
            if (profile.getProfilePhotoUrl() != null) {
                user.setProfile_picture(profile.getProfilePhotoUrl());
            }
            if (profile.getPhoneNumber() != null) {
                user.setPhone(profile.getPhoneNumber());
            }



                return userRepository.save(user);
        } else {
            System.out.println("hola");
            throw new ProfileDataException("Perfil no encontrado");
        }
    }
}
