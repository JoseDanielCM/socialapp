package com.socialmedia.socialapp.DbEntity.Notifications;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.socialmedia.socialapp.DbEntity.Notifications.DTO.NotificationType;
import com.socialmedia.socialapp.DbEntity.User.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user_notification-notifications")
    private User user_notification;

    private NotificationType notification_type;

    private Long reference_id;

    private Boolean is_read;

    private LocalDate created_at;

    public Notification() {
    }

    public Notification(Long id, User user_notification, NotificationType notification_type, Long reference_id, Boolean is_read, LocalDate created_at) {
        this.id = id;
        this.user_notification = user_notification;
        this.notification_type = notification_type;
        this.reference_id = reference_id;
        this.is_read = is_read;
        this.created_at = created_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser_notification() {
        return user_notification;
    }

    public void setUser_notification(User user_notification) {
        this.user_notification = user_notification;
    }

    public NotificationType getNotification_type() {
        return notification_type;
    }

    public void setNotification_type(NotificationType notification_type) {
        this.notification_type = notification_type;
    }

    public Long getReference_id() {
        return reference_id;
    }

    public void setReference_id(Long reference_id) {
        this.reference_id = reference_id;
    }

    public Boolean getIs_read() {
        return is_read;
    }

    public void setIs_read(Boolean is_read) {
        this.is_read = is_read;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", user_notification=" + user_notification +
                ", notification_type=" + notification_type +
                ", reference_id=" + reference_id +
                ", is_read=" + is_read +
                ", created_at=" + created_at +
                '}';
    }
}
