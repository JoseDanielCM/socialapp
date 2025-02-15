package com.socialmedia.socialapp.DbEntity.Post;

import com.socialmedia.socialapp.DbEntity.Post.DTO.CreatePostDTO;
import com.socialmedia.socialapp.DbEntity.User.User;
import com.socialmedia.socialapp.DbEntity.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;
    public void createPost(CreatePostDTO createPostDTO) {
        Post post = new Post();

        User user = userService.getUser(createPostDTO.getUser_id()).getBody();
        post.setUser_post(user);
        post.setTitle(createPostDTO.getTitle());
        post.setContent(createPostDTO.getContent());
        post.setImg_url(createPostDTO.getImg_url());
        postRepository.save(post);

    }
}
