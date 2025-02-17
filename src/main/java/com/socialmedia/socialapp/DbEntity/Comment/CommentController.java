package com.socialmedia.socialapp.DbEntity.Comment;

import com.socialmedia.socialapp.DbEntity.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/userByCommentId/{id}")
    public User getUserByCommentId(@PathVariable Long id) {
        return commentService.getUserByCommentId(id);
    }

    @GetMapping("/getByPost/{postId}")
    public List<Comment> getCommentsByPost(@PathVariable Long postId) {
        return commentService.getByPost(postId);
    }

    // add comment
    @PostMapping("/postComment/{post_id}/{user_id}/{comment_text}")
    public ResponseEntity<Comment> postComment(@PathVariable Long post_id, @PathVariable Long user_id, @PathVariable String comment_text) {

        return ResponseEntity.ok(commentService.postComment(post_id, user_id, comment_text)) ;
    }
}
