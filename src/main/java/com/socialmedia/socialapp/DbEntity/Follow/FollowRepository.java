package com.socialmedia.socialapp.DbEntity.Follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow,Long> {

    @Query("SELECT f FROM Follow f WHERE f.user_follow.username = :username")
    List<Follow> findFollowersByUsername(@Param("username") String username);}
