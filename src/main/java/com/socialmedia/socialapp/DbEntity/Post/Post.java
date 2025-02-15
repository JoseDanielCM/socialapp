package com.socialmedia.socialapp.DbEntity.Post;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.socialmedia.socialapp.DbEntity.Comment.Comment;
import com.socialmedia.socialapp.DbEntity.Like.Like;
import com.socialmedia.socialapp.DbEntity.Tag.Tag;
import com.socialmedia.socialapp.DbEntity.User.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private String title;
    private String content;
    private String img_url;
    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @ManyToMany
    @JoinTable(
            name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

// RELACIONES

    // LIKE
    @OneToMany(mappedBy = "post_like") // Follow -> user_data
    @JsonManagedReference("post_like-likes")
    private List<Like> likes;  // Users that this user is following

    // COMMENT
    @OneToMany(mappedBy = "commented_post") // Follow -> user_data
    @JsonManagedReference("commented_post-comments")
    private List<Comment> comments;  // Users that this user is following
    public Post() {
    }

    public Post(Long id, User user_post, String title, String content, String img_url, LocalDateTime created_at, LocalDateTime updated_at, Set<Tag> tags, List<Like> likes, List<Comment> comments) {
        this.id = id;
        this.user_post = user_post;
        this.title = title;
        this.content = content;
        this.img_url = img_url;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.tags = tags;
        this.likes = likes;
        this.comments = comments;
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


    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
