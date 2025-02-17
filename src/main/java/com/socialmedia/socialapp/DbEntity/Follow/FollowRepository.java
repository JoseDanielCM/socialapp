package com.socialmedia.socialapp.DbEntity.Follow;

import com.socialmedia.socialapp.DbEntity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow,Long> {

    @Query("SELECT f.user_follow FROM Follow f WHERE f.followed_user.username = :username")
    List<User> findFollowersByUsername(@Param("username") String username);

    @Query("SELECT f.followed_user FROM Follow f WHERE f.user_follow.username = :username")
    List<User> findFollowsByUsername(@Param("username") String username);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN TRUE ELSE FALSE END FROM Follow f WHERE f.user_follow = :userFollow AND f.followed_user = :followedUser")
    boolean existsByFollowerAndFollowing(@Param("userFollow") User user_follow, @Param("followedUser") User followed_user);

    @Query("SELECT f FROM Follow f WHERE f.user_follow = :follower AND f.followed_user = :followed")
    Optional<Follow> findByUserOriginalAndUserView(@Param("follower") User follower,@Param("followed") User followed);
}
