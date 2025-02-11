package com.socialmedia.socialapp.DbEntity.Like;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.socialmedia.socialapp.DbEntity.Post.Post;
import com.socialmedia.socialapp.DbEntity.User.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "likes", uniqueConstraints = @UniqueConstraint(columnNames =  {"user_id","followed_user_id"}))
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // cual fue el post al que se le dio like
    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference("post_like-likes")
    private Post post_like;

    // Cual fue el user que dio like
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user_like-likes")
    private User user_like;

    private LocalDate created_at;

    public Like() {
    }

    public Like(Long id, Post post_like, User user_like, LocalDate created_at) {
        this.id = id;
        this.post_like = post_like;
        this.user_like = user_like;
        this.created_at = created_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost_like() {
        return post_like;
    }

    public void setPost_like(Post post_like) {
        this.post_like = post_like;
    }

    public User getUser_like() {
        return user_like;
    }

    public void setUser_like(User user_like) {
        this.user_like = user_like;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", post_like=" + post_like +
                ", user_like=" + user_like +
                ", created_at=" + created_at +
                '}';
    }
}
