package com.socialmedia.socialapp.DbEntity.Follow;

import com.socialmedia.socialapp.DbEntity.Notifications.NotificationService;
import com.socialmedia.socialapp.DbEntity.User.User;
import com.socialmedia.socialapp.DbEntity.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FollowService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FollowRepository followRepository;

    @Autowired
    NotificationService notificationService;

    public boolean userHaveFollowed(Long idOg,Long idView) {

        User userOriginal = userRepository.findById(idOg)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        User userView = userRepository.findById(idView)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return followRepository.existsByFollowerAndFollowing(userOriginal, userView);

    }

    public void deleteFollow(Long idOg, Long idView) {
        User userOriginal = userRepository.findById(idOg)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        User userView = userRepository.findById(idView)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Follow follow = followRepository.findByUserOriginalAndUserView(userOriginal, userView)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Follow relationship not found"));

        followRepository.delete(follow);
    }

    public void addFollow(Long idOg, Long idView) {
        User userOriginal = userRepository.findById(idOg)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        User userView = userRepository.findById(idView)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Follow follow =new Follow();
        follow.setuser_follow(userOriginal);
        follow.setFollowed_user(userView);
        followRepository.save(follow);

        notificationService.sendNotification( "New follow from " + userOriginal.getUsername() + " to " + userView.getUsername() + "!", userView.getId(), userOriginal  );

    }
}
