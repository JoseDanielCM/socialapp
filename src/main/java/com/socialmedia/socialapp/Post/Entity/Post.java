package com.socialmedia.socialapp.Post.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.socialmedia.socialapp.User.Entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user_post-posts")
    private User user_post;

    private String content;
    private String img_url;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Post() {
    }

    public Post(Long id, User user_post, String content, String img_url, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.user_post = user_post;
        this.content = content;
        this.img_url = img_url;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser_post() {
        return user_post;
    }

    public void setUser_post(User user_post) {
        this.user_post = user_post;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
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
        return "Post{" +
                "id=" + id +
                ", user_post=" + user_post +
                ", content='" + content + '\'' +
                ", img_url='" + img_url + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
