package com.socialmedia.socialapp.DbEntity.Notifications;

import com.socialmedia.socialapp.DbEntity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    @Query("SELECT n  FROM Notification n WHERE n.user_notification = :user")
    List<Notification> findByUser(User user);
}
