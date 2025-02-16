package com.socialmedia.socialapp.DbEntity.Like;

import com.socialmedia.socialapp.DbEntity.Post.Post;
import com.socialmedia.socialapp.DbEntity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {


    Optional<Like> findByPostlikeAndUserlike(Post postlike, User userlike);
}
