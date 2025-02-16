package com.socialmedia.socialapp.DbEntity.Post;


import com.socialmedia.socialapp.DbEntity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post,Long> {

    @Query("SELECT p.user_post FROM Post p WHERE p.id = :postId")
    User findUserByPostId(@Param("postId") Long postId);
}
