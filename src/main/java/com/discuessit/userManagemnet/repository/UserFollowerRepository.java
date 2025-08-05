package com.discuessit.userManagemnet.repository;

import com.discuessit.userManagemnet.model.UserFollower;
import com.discuessit.userManagemnet.model.UserFollowerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFollowerRepository extends JpaRepository<UserFollower, UserFollowerId> {
    List<UserFollower> findByUserId(Long userId);
    List<UserFollower> findByFollowerId(Long followerId);
}
