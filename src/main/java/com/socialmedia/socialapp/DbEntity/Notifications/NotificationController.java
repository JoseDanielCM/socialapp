package com.socialmedia.socialapp.DbEntity.Notifications;

import com.socialmedia.socialapp.DbEntity.Notifications.DTO.MentionNotificationRequest;
import com.socialmedia.socialapp.DbEntity.Notifications.DTO.NotificationType;
import com.socialmedia.socialapp.DbEntity.User.User;
import com.socialmedia.socialapp.DbEntity.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/getAll")
    public List<Notification> getNotifications() {
        return notificationRepository.findAll();
    }

    @PostMapping("/notify-mentions")
    public ResponseEntity<?> notifyMentions(@RequestBody MentionNotificationRequest request) {

        System.out.println(request);

        List<User> mentionedUsers = userRepository.findByUsernameIn(request.getMentioned_users());

        for (User user : mentionedUsers) {
            if (!user.getId().equals(request.getCommenter_id())) { // Evita auto-notificaciones
                Notification notification = new Notification();
                notification.setIs_read(false);
                notification.setNotification_type(NotificationType.MENTION);
                notification.setReference_id(request.getComment_id()); // Enlace al comentario
                notification.setUser_notification(user); // Usuario que recibe la notificación

                notificationRepository.save(notification);
            }
        }
        return ResponseEntity.ok("Notifications created");
    }
}
