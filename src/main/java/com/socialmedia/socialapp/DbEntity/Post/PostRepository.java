package com.socialmedia.socialapp.DbEntity.Post;


import com.socialmedia.socialapp.DbEntity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    @Query("SELECT p.user_post FROM Post p WHERE p.id = :postId")
    User findUserByPostId(@Param("postId") Long postId);

    @Query("SELECT p FROM Post p WHERE p.user_post.id IN :userIds ORDER BY p.created_at DESC")
    List<Post> findPostsByFollowingUsers(@Param("userIds") List<Long> userIds);

    @Query("""
    SELECT p FROM Post p 
    LEFT JOIN p.likes l 
    LEFT JOIN p.comments c
    WHERE p.user_post.id IN :userIds 
    AND p.created_at >= :startDate
    GROUP BY p 
    ORDER BY (COUNT(l) + COUNT(c)) DESC
""")
    List<Post> findPostsByFollowingUsersPopularity(
            @Param("userIds") List<Long> userIds,
            @Param("startDate") LocalDateTime startDate
    );


}
