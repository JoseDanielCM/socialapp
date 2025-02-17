package com.socialmedia.socialapp.DbEntity.Post;

import com.socialmedia.socialapp.DbEntity.Follow.Follow;
import com.socialmedia.socialapp.DbEntity.Post.DTO.CreatePostDTO;
import com.socialmedia.socialapp.DbEntity.User.User;
import com.socialmedia.socialapp.DbEntity.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public List<Post> findAllByDate(Long user_id) {
        User user = userService.getUser(user_id).getBody();
        List<Follow> followsIds = user.getFollows();

        List<Long> listIdsFollows = new ArrayList<Long>();
        for (Follow follow : followsIds) {
            listIdsFollows.add(follow.getFollowed_user().getId());
        }

        return postRepository.findPostsByFollowingUsers(listIdsFollows);

    }

    public List<Post> findAllByInteractions(Long id) {
        User user = userService.getUser(id).getBody();
        List<Follow> followsIds = user.getFollows();

        List<Long> listIdsFollows = new ArrayList<Long>();
        for (Follow follow : followsIds) {
            listIdsFollows.add(follow.getFollowed_user().getId());
        }
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);

        return postRepository.findPostsByFollowingUsersPopularity(listIdsFollows, oneMonthAgo);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }
}
