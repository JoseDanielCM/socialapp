package com.socialmedia.socialapp.DbEntity.Notifications;

import com.socialmedia.socialapp.DbEntity.Notifications.DTO.MentionNotificationRequest;
import com.socialmedia.socialapp.DbEntity.Notifications.DTO.NotificationType;
import com.socialmedia.socialapp.DbEntity.User.User;
import com.socialmedia.socialapp.DbEntity.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    NotificationService notificationService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/sendNotification")  // Mapea el destino del mensaje
    public void sendNotification(Notification notification) {
        messagingTemplate.convertAndSend("/topic/notifications", notification);  // Envia el mensaje a todos los suscriptores
    }

    @GetMapping("/getAll")
    public List<Notification> getNotifications() {
        return notificationRepository.findAll();
    }

    @PostMapping("/notify-mentions")
    public ResponseEntity<?> notifyMentions(@RequestBody MentionNotificationRequest request) {

        System.out.println(request);

        User commenter = userRepository.findById(request.getCommenter_id()).orElseThrow(() -> new RuntimeException("User not found"));

        List<User> mentionedUsers = userRepository.findByUsernameIn(request.getMentioned_users());

        for (User user : mentionedUsers) {
            if (!user.getId().equals(request.getCommenter_id())) { // Evita auto-notificaciones
                Notification notification = new Notification();
                notification.setIs_read(false);
                notification.setNotification_type(NotificationType.MENTION);
                notification.setReference_id(request.getComment_id()); // Enlace al comentario
                notification.setUser_notification(user); // Usuario que recibe la notificación

                if (! (request.getCommenter_id() == user.getId())) {
                    notificationService.sendNotification( commenter.getUsername() + " mentioned you in a comment!",user.getId()  );

                }

                notificationRepository.save(notification);
            }
        }
        return ResponseEntity.ok("Notifications created");
    }


}
