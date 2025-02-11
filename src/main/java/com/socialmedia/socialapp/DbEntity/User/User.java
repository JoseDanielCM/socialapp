package com.socialmedia.socialapp.DbEntity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.socialmedia.socialapp.DbEntity.Comment.Comment;
import com.socialmedia.socialapp.DbEntity.Like.Like;
import com.socialmedia.socialapp.DbEntity.Notifications.Notification;
import com.socialmedia.socialapp.DbEntity.Post.Post;
import com.socialmedia.socialapp.DbEntity.User.DTO.Role;
import com.socialmedia.socialapp.DbEntity.Follow.Follow;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String first_name;
    @Column(nullable = false)
    private String last_name;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private LocalDate birthdate;
    private Long phone;
    private String bio;
    private String profile_picture;
    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime registered_at;
    @Enumerated(EnumType.STRING)
    Role role;

// RELACIONES

// FOLLOW
    @OneToMany(mappedBy = "user_follow") // Follow -> user_data
    @JsonIgnore
    private List<Follow> follows;  // Users that this user is following

    @OneToMany(mappedBy = "followed_user")
    @JsonIgnore
    private List<Follow> followers; // Users that follow this user
// Post
    @OneToMany(mappedBy = "user_post") //
    @JsonManagedReference("user_post-posts")
    private List<Post> posts; // posts of the user
// Notification
    @OneToMany(mappedBy = "user_notification")
    @JsonManagedReference("user_notification-notifications")
    private List<Notification> notifications; // notifications of the user

// Like
    @OneToMany(mappedBy = "user_like")
    @JsonManagedReference("user_like-likes")
    private List<Like> likes;
// Comment
    @OneToMany(mappedBy = "commented_by_user")
    @JsonManagedReference("commented_by_user-comments")
    private List<Comment> comments;
    // -----------------------

    public User() {
    }

    public User(Long id, String first_name, String last_name, String username, String password, String email, LocalDate date, Long phone, String bio, String profile_picture, LocalDateTime registered_at, Role role, List<Follow> follows, List<Follow> followers, List<Post> posts, List<Notification> notifications, List<Like> likes, List<Comment> comments) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthdate = date;
        this.phone = phone;
        this.bio = bio;
        this.profile_picture = profile_picture;
        this.registered_at = registered_at;
        this.role = role;
        this.follows = follows;
        this.followers = followers;
        this.posts = posts;
        this.notifications = notifications;
        this.likes = likes;
        this.comments = comments;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public LocalDateTime getRegistered_at() {
        return registered_at;
    }

    public void setRegistered_at(LocalDateTime registered_at) {
        this.registered_at = registered_at;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public List<Follow> getFollows() {
        return follows;
    }

    public void setFollows(List<Follow> follows) {
        this.follows = follows;
    }

    public List<Follow> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Follow> followers) {
        this.followers = followers;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", birthdate=" + birthdate +
                ", phone=" + phone +
                ", bio='" + bio + '\'' +
                ", profile_picture='" + profile_picture + '\'' +
                ", registered_at=" + registered_at +
                ", role=" + role +
                ", follows=" + follows +
                ", followers=" + followers +
                ", posts=" + posts +
                ", notifications=" + notifications +
                ", likes=" + likes +
                ", comments=" + comments +
                '}';
    }
}
