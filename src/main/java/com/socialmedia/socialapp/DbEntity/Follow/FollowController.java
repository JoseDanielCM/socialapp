package com.socialmedia.socialapp.DbEntity.Follow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/follows")
public class FollowController {

    @Autowired
    FollowService followService;

    @GetMapping("isFollowed/{id_og}/{id_view}")
    public boolean isFollowed(@PathVariable Long id_og, @PathVariable Long id_view) {
        return followService.userHaveFollowed(id_og, id_view);

    }

    @DeleteMapping("unfollow/{id_og}/{id_view}")
    public void deleteFollow(@PathVariable Long id_og, @PathVariable Long id_view) {
        followService.deleteFollow(id_og, id_view);

    }

    @PostMapping("follow/{id_og}/{id_view}")
    public void addFollow(@PathVariable Long id_og, @PathVariable Long id_view) {
        followService.addFollow(id_og, id_view);

    }
}
