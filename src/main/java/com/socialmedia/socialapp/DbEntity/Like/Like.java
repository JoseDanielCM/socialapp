package com.socialmedia.socialapp.DbEntity.Like;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.socialmedia.socialapp.DbEntity.Post.Post;
import com.socialmedia.socialapp.DbEntity.User.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "likes", uniqueConstraints = @UniqueConstraint(columnNames =  {"user_id","post_id"}))
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // cual fue el post al que se le dio like
    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference("post_like-likes")
    private Post postlike;

    // Cual fue el user que dio like
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user_like-likes")
    private User userlike;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime created_at;

    public Like() {
    }

    public Like(Long id, Post postlike, User userlike, LocalDateTime created_at) {
        this.id = id;
        this.postlike = postlike;
        this.userlike = userlike;
        this.created_at = created_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPostlike() {
        return postlike;
    }

    public void setPostlike(Post postlike) {
        this.postlike = postlike;
    }

    public User getUserlike() {
        return userlike;
    }

    public void setUserlike(User userlike) {
        this.userlike = userlike;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", postlike=" + postlike +
                ", userlike=" + userlike +
                ", created_at=" + created_at +
                '}';
    }
}
