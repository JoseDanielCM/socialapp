package com.socialmedia.socialapp.DbEntity.Like;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @DeleteMapping("/delete/{id_post}/{user_id}")
    public void deleteLikeByPostId(@PathVariable Long id_post, @PathVariable Long user_id) {
        // Implement logic to delete like by post id
        likeService.deleteLike(id_post, user_id);
    }

    @PostMapping("/add/{id_post}/{user_id}")
    public void addLikeByPostId(@PathVariable Long id_post, @PathVariable Long user_id) {
        System.out.println("----------------------------------------------------------------");
        System.out.println(id_post);
        System.out.println(user_id);
        System.out.println("----------------------------------------------------------------");
        likeService.addLike(id_post, user_id);
    }

    @GetMapping("/userHaveLiked/{id_post}/{user_id}")
    public boolean userHaveLiked(@PathVariable Long id_post, @PathVariable Long user_id) {
        // Implement logic to check if user has liked the post
        return likeService.userHaveLiked(id_post, user_id);
    }
}
