package com.socialmedia.socialapp.DbEntity.Post;


import com.socialmedia.socialapp.DbEntity.Post.DTO.CreatePostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostService postService;
    @PostMapping("/createPost")
    public void createPost(@RequestBody CreatePostDTO createPostDTO) {
        System.out.println(createPostDTO);

        postService.createPost(createPostDTO);

    }

}
