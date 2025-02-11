package com.socialmedia.socialapp.DbEntity.Follow;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.socialmedia.socialapp.DbEntity.User.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "follows", uniqueConstraints = @UniqueConstraint(columnNames =  {"user_id","followed_user_id"}))
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference("user_follow-follows")
    private User user_follow;

    @ManyToOne
    @JoinColumn(name = "followed_user_id")
    @JsonBackReference("user_follow-followers")
    private User followed_user;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime registered_at;

    public Follow() {
    }

    public Follow(Long id, User user_follow, User followed_user, LocalDateTime registered_at) {
        this.id = id;
        this.user_follow = user_follow;
        this.followed_user = followed_user;
        this.registered_at = registered_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getuser_follow() {
        return user_follow;
    }

    public void setuser_follow(User user_follow) {
        this.user_follow = user_follow;
    }

    public User getFollowed_user() {
        return followed_user;
    }

    public void setFollowed_user(User followed_user) {
        this.followed_user = followed_user;
    }

    public LocalDateTime getRegistered_at() {
        return registered_at;
    }

    public void setRegistered_at(LocalDateTime registered_at) {
        this.registered_at = registered_at;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "id=" + id +
                ", user_follow=" + user_follow +
                ", followed_user=" + followed_user +
                ", registered_at=" + registered_at +
                '}';
    }
}
