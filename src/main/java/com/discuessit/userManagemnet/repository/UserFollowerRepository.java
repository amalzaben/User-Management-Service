package com.discuessit.userManagemnet.repository;

import com.discuessit.userManagemnet.model.User;
import com.discuessit.userManagemnet.model.UserFollower;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFollowerRepository extends JpaRepository<UserFollower, Long> {

    Optional<UserFollower> findByFollowedUserAndFollower(User followedUser, User follower);
    Page<UserFollower> findByFollowerIdAndDeletedFalse(Long followerId, Pageable pageable);
    Page<UserFollower> findByFollowedUserIdAndDeletedFalse(Long userId, Pageable pageable);
}
