package com.socialmedia.socialapp.DbEntity.Post;


import com.socialmedia.socialapp.DbEntity.Post.DTO.CreatePostDTO;
import com.socialmedia.socialapp.DbEntity.Tag.Tag;
import com.socialmedia.socialapp.DbEntity.Tag.TagRepository;
import com.socialmedia.socialapp.DbEntity.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    TagRepository tagRepository;

    @PostMapping("/createPost")
    public ResponseEntity<Post> createPost(@RequestBody CreatePostDTO createPostDTO) {
        System.out.println(createPostDTO);

        return ResponseEntity.ok(postService.createPost(createPostDTO));

    }

    @GetMapping("/getPosts")
    public List<Post> getAllPosts() {
        return postService.findAll();
    }

    @GetMapping("/getPost/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @GetMapping("/getUserByPost/{id}")
    public ResponseEntity<User> getUserByPost(@PathVariable Long id) {
        User user = postRepository.findUserByPostId(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        System.out.println(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/{postId}/tags")
    public ResponseEntity<Post> assignTagsToPost(@PathVariable Long postId, @RequestBody List<String> tags) {
        System.out.println("🚀 Recibí una solicitud para asignar tags!");
        System.out.println("📌 postId: " + postId);
        System.out.println("📌 tags: " + tags);
        System.out.println("📌 Authentication: " + SecurityContextHolder.getContext().getAuthentication());  // Add this line

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        List<Tag> tagsList = tags.stream()
                .map(name -> tagRepository.findByName(name)
                .orElseGet(() -> {
                    Tag tag = new Tag();
                    tag.setName(name);
                    return tagRepository.save(tag);
                }))
                .collect(Collectors.toList());

        post.getTags().addAll(tagsList);
        postRepository.save(post);

        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts/test-auth")
    public ResponseEntity<String> testAuth(Authentication authentication) {
        System.out.println("🔍 Test endpoint reached");
        System.out.println("🔍 Authentication: " + authentication);
        return ResponseEntity.ok("Authenticated as: " + authentication.getName());
    }

}

