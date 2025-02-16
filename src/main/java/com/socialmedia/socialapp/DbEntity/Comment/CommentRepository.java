package com.socialmedia.socialapp.DbEntity.Comment;

import com.socialmedia.socialapp.DbEntity.Post.Post;
import com.socialmedia.socialapp.DbEntity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query("SELECT c.commented_by_user FROM Comment c WHERE c.id = :id")
    User getUserByCommentId(Long id);

    @Query("SELECT c FROM Comment c WHERE c.commented_post = :post")
    List<Comment> findByCommented_post(@Param("post") Post post);}
