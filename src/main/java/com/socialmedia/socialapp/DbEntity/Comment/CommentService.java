package com.socialmedia.socialapp.DbEntity.Comment;

import com.socialmedia.socialapp.DbEntity.Post.Post;
import com.socialmedia.socialapp.DbEntity.Post.PostRepository;
import com.socialmedia.socialapp.DbEntity.User.User;
import com.socialmedia.socialapp.DbEntity.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    public User getUserByCommentId(Long id) {
        return commentRepository.getUserByCommentId(id);
    }

    public Comment postComment(Long postId, Long userId, String comment_text) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Post> post = postRepository.findById(postId);
        Comment comment = new Comment();

        if (user.isPresent() && post.isPresent()) {
            comment.setCommented_by_user(user.get());
            comment.setCommented_post(post.get());
            comment.setContent(comment_text);
            System.out.println("valeee");
            return commentRepository.save(comment);
        }
        System.out.println("no estan");
        return null;
    }

    public List<Comment> getByPost(Long postId) {
        return commentRepository.findByCommented_post(postRepository.findById(postId).orElse(null));
    }
}
