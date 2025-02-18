package com.socialmedia.socialapp.DbEntity.Notifications;

import com.socialmedia.socialapp.DbEntity.Notifications.DTO.NotificationType;
import com.socialmedia.socialapp.DbEntity.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendNotification(String message, Long userId, User user) {
        Map<String, Object> notification = new HashMap<>();
        notification.put("message", message);
        notification.put("userId", userId);
        notification.put("user", user);

        messagingTemplate.convertAndSend("/topic/notifications", notification);
    }


}
