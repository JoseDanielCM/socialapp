package com.socialmedia.socialapp.DbEntity.Like;


import com.socialmedia.socialapp.DbEntity.Notifications.NotificationService;
import com.socialmedia.socialapp.DbEntity.Post.Post;
import com.socialmedia.socialapp.DbEntity.Post.PostRepository;
import com.socialmedia.socialapp.DbEntity.User.User;
import com.socialmedia.socialapp.DbEntity.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class LikeService {

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    NotificationService notificationService;

    public void deleteLike(Long idPost, Long userId) {
        Post post_like = postRepository.findById(idPost)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));

        User user_like = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Optional<Like> like = likeRepository.findByPostlikeAndUserlike(post_like, user_like);

        likeRepository.delete(like.get());

    }

    public void addLike(Long idPost, Long userId) {
        Like like = new Like();

        Post post = postRepository.findById(idPost)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));


        like.setPostlike(post);
        like.setUserlike(user);

        if (!post.getUser_post().getId().equals(user.getId())) {
            notificationService.sendNotification("New like on your post: " + post.getTitle() + " by " + user.getUsername() + "!", post.getUser_post().getId(), user  );

        }

        likeRepository.save(like);
    }

    public boolean userHaveLiked(Long idPost, Long userId) {
        Post post = postRepository.findById(idPost)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));

        User user = userRepository.findById(userId)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        return likeRepository.findByPostlikeAndUserlike(post, user).isPresent();
    }
}
