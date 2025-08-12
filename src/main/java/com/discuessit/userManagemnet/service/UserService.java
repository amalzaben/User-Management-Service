package com.discuessit.userManagemnet.service;

import com.discuessit.userManagemnet.mapper.dto.controllerDTO.PaginatedResponse;
import com.discuessit.userManagemnet.mapper.dto.serviceDTO.RegisterUserCommand;
import com.discuessit.userManagemnet.mapper.dto.controllerDTO.UserFollowerResponse;
import com.discuessit.userManagemnet.mapper.UserFollowerMapper;
import com.discuessit.userManagemnet.mapper.UserMapper;
import com.discuessit.userManagemnet.model.User;
import com.discuessit.userManagemnet.model.UserFollower;
import com.discuessit.userManagemnet.repository.UserFollowerRepository;
import com.discuessit.userManagemnet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserFollowerRepository userFollowerRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserFollowerMapper userFollowerMapper;

    public User registerUser(RegisterUserCommand command) {
        User user = userMapper.toEntity(command);
        return userRepository.save(user);
    }

    public User login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new RuntimeException("Invalid username or password!");
        }

        User loggedInuser = user.get();

        //temporary since i didn't hash the password yet
        if (!loggedInuser.getPassword().equals(password)) {
            throw new RuntimeException("Invalid username or password!");
        }

        return loggedInuser;
    }

    public void followUser(Long userId, Long followerId) {
        if (userId.equals(followerId)) {
            throw new IllegalArgumentException("Users cannot follow themselves");
        }

        User followedUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Follower not found"));

        // Find existing UserFollower relation (even if deleted)
        Optional<UserFollower> existingRelation = userFollowerRepository
                .findByFollowedUserAndFollower(followedUser, follower);

        if (existingRelation.isPresent()) {
            UserFollower userFollower = existingRelation.get();

            if (!userFollower.isDeleted()) {
                throw new RuntimeException("You are already following this user");
            }

            // Reactivate soft-deleted relationship
            userFollower.setDeleted(false);
            userFollowerRepository.save(userFollower);
            return;
        }

        // No existing relation - create new
        UserFollower userFollower = new UserFollower();
        userFollower.setFollowedUser(followedUser);
        userFollower.setFollower(follower);

        userFollowerRepository.save(userFollower);
    }

    public void unfollowUser(Long followedUserId, Long followerId) {
        User followedUser = userRepository.findById(followedUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Follower not found"));

        Optional<UserFollower> relationOpt = userFollowerRepository.findByFollowedUserAndFollower(followedUser, follower);

        if (relationOpt.isEmpty() || relationOpt.get().isDeleted()) {
            throw new RuntimeException("Follow relationship does not exist.");
        }

        UserFollower relation = relationOpt.get();
        relation.setDeleted(true);

        userFollowerRepository.save(relation);
    }

    public PaginatedResponse<UserFollowerResponse> listFollowers(Long userId, Pageable pageable) {
        Page<UserFollower> followersPage = userFollowerRepository.findByFollowedUserIdAndDeletedFalse(userId, pageable);

        List<UserFollowerResponse> content = followersPage.stream()
                .map(userFollowerMapper::toDto)
                .collect(Collectors.toList());

        return new PaginatedResponse<>(
                content,
                followersPage.getNumber(),
                followersPage.getSize(),
                followersPage.getTotalElements(),
                followersPage.getTotalPages(),
                followersPage.isLast()
        );
    }


    public PaginatedResponse<UserFollowerResponse> listFollowing(Long userId, Pageable pageable) {
        Page<UserFollower> followingPage = userFollowerRepository.findByFollowerIdAndDeletedFalse(userId, pageable);

        List<UserFollowerResponse> content = followingPage.stream()
                .map(userFollowerMapper::toDto)
                .collect(Collectors.toList());

        return new PaginatedResponse<>(
                content,
                followingPage.getNumber(),
                followingPage.getSize(),
                followingPage.getTotalElements(),
                followingPage.getTotalPages(),
                followingPage.isLast()
        );
    }
}

// i still have points logic, community logic