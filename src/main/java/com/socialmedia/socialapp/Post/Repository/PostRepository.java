package com.socialmedia.socialapp.Post.Repository;

import com.socialmedia.socialapp.Post.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
