package com.socialmedia.socialapp.DbEntity.Comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.socialmedia.socialapp.DbEntity.Post.Post;
import com.socialmedia.socialapp.DbEntity.User.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("commented_by_user-likes")
    private User commented_by_user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference("commented_post-likes")
    private Post commented_post;

    private String content;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Comment() {
    }

    public Comment(Long id, User commented_by_user, Post commented_post, String content, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.commented_by_user = commented_by_user;
        this.commented_post = commented_post;
        this.content = content;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCommented_by_user() {
        return commented_by_user;
    }

    public void setCommented_by_user(User commented_by_user) {
        this.commented_by_user = commented_by_user;
    }

    public Post getCommented_post() {
        return commented_post;
    }

    public void setCommented_post(Post commented_post) {
        this.commented_post = commented_post;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", commented_by_user=" + commented_by_user +
                ", commented_post=" + commented_post +
                ", content='" + content + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
