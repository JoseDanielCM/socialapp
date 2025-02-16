package com.socialmedia.socialapp.DbEntity.Post;

import com.socialmedia.socialapp.DbEntity.Post.DTO.CreatePostDTO;
import com.socialmedia.socialapp.DbEntity.User.User;
import com.socialmedia.socialapp.DbEntity.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;
    public Post createPost(CreatePostDTO createPostDTO) {
        Post post = new Post();

        User user = userService.getUser(createPostDTO.getUser_id()).getBody();
        post.setUser_post(user);
        post.setTitle(createPostDTO.getTitle());
        post.setContent(createPostDTO.getContent());
        post.setImg_url(createPostDTO.getImg_url());
        return postRepository.save(post);

    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }
}
