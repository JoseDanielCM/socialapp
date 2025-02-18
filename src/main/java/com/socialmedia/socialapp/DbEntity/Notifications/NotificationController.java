package com.socialmedia.socialapp.DbEntity.Notifications;

import com.socialmedia.socialapp.DbEntity.Comment.Comment;
import com.socialmedia.socialapp.DbEntity.Comment.CommentRepository;
import com.socialmedia.socialapp.DbEntity.Follow.Follow;
import com.socialmedia.socialapp.DbEntity.Follow.FollowRepository;
import com.socialmedia.socialapp.DbEntity.Like.Like;
import com.socialmedia.socialapp.DbEntity.Like.LikeRepository;
import com.socialmedia.socialapp.DbEntity.Notifications.DTO.MentionNotificationRequest;
import com.socialmedia.socialapp.DbEntity.Notifications.DTO.NotificationType;
import com.socialmedia.socialapp.DbEntity.User.User;
import com.socialmedia.socialapp.DbEntity.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    FollowRepository followRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    LikeRepository likeRepository;
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
                    notificationService.sendNotification( commenter.getUsername() + " mentioned you in a comment!",user.getId(), commenter  );

                }

                notificationRepository.save(notification);
            }
        }
        return ResponseEntity.ok("Notifications created");
    }

    @GetMapping("/getByUser/{id}")
    public List<Notification> getNotificationsByUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        System.out.println(user);
        return notificationRepository.findByUser(user.orElse(null));
    }

    @GetMapping("/getUserByNotification/{id}")
    public User getUserOriginalByNotification(@PathVariable Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);

        User user = null; // Variable para almacenar al usuario
        Notification notif = notification.get();
        Long referenceId = notification.get().getReference_id();

        switch (notification.get().getNotification_type()) {
            case LIKE:
                Like like = likeRepository.findById(referenceId)
                        .orElseThrow(() -> new RuntimeException("Like not found"));

                user = like.getUserlike();
                break;
            case COMMENT:
                Comment comment = commentRepository.findById(referenceId)
                        .orElseThrow(() -> new RuntimeException("Comment not found"));

                user = comment.getCommented_by_user();
                break;
            case MENTION:
                Comment commentMent = commentRepository.findById(referenceId)
                        .orElseThrow(() -> new RuntimeException("Comment not found"));

                user = commentMent.getCommented_by_user();
                break;
            case FOLLOW:
                // Para FOLLOW, asumimos que el reference_id se refiere a un follow (por ejemplo, un ID de relación de seguidores)
                Follow follow = followRepository.findById(referenceId)
                        .orElseThrow(() -> new RuntimeException("Follow not found"));

                user = follow.getuser_follow();
                break;
            default:
                throw new RuntimeException("Invalid notification type");
        }
        return user;

    }

    @PutMapping("/markAsRead/{id}")
    public void markNotificationAsRead(@PathVariable Long id){
        Optional<Notification> optNotification = notificationRepository.findById(id);
        Notification notification = optNotification.get();
        notification.setIs_read(true);
        notificationRepository.save(notification);
    }
}
