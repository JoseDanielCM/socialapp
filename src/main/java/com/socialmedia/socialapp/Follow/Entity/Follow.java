package com.socialmedia.socialapp.Follow.Entity;

import com.socialmedia.socialapp.User.Entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "follows", uniqueConstraints = @UniqueConstraint(columnNames =  {"user_id","followed_user_id"}))
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "followed_user_id", insertable = false, updatable = false)
    private User followed_user;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime registered_at;

}
