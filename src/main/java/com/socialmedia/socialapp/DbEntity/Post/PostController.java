package com.socialmedia.socialapp.DbEntity.Post;


import com.socialmedia.socialapp.DbEntity.Post.DTO.CreatePostDTO;
import com.socialmedia.socialapp.DbEntity.Post.DTO.UpdatePostDTO;
import com.socialmedia.socialapp.DbEntity.Tag.Tag;
import com.socialmedia.socialapp.DbEntity.Tag.TagRepository;
import com.socialmedia.socialapp.DbEntity.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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

    @GetMapping("/getPostsByDate/{user_id}")
    public List<Post> getAllPostsByDateAndFollow(@PathVariable Long user_id) {
        return postService.findAllByDate(user_id);
    }

    @GetMapping("/getPostsByInteractions/{id}")
    public List<Post> getAllPostsByInteractions(@PathVariable Long id) {
        return postService.findAllByInteractions(id);
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

    @PutMapping("/update/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody UpdatePostDTO updatedPost) {
        System.out.println(updatedPost);
        System.out.println(id);
        // Buscar el post por ID (suponiendo que tienes un servicio para esto)
        Optional<Post> existingPost = Optional.ofNullable(postService.findById(id));
        if (existingPost.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Post post = existingPost.get();
        post.setTitle(updatedPost.getTitle());
        post.setContent(updatedPost.getContent());
        post.setImg_url(updatedPost.getImg_url());
        post.setUpdated_at(LocalDateTime.now());
        System.out.println(post);
        // Guardar cambios
        Post savedPost = postService.save(post);
        return ResponseEntity.ok(savedPost);
    }

}

