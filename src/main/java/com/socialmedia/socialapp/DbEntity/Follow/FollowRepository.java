package com.socialmedia.socialapp.DbEntity.Follow;

import com.socialmedia.socialapp.DbEntity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow,Long> {

    @Query("SELECT f.user_follow FROM Follow f WHERE f.followed_user.username = :username")
    List<User> findFollowersByUsername(@Param("username") String username);

    @Query("SELECT f.followed_user FROM Follow f WHERE f.user_follow.username = :username")
    List<User> findFollowsByUsername(@Param("username") String username);
}
