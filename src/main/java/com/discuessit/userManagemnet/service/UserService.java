package com.discuessit.userManagemnet.service;

import com.discuessit.userManagemnet.dto.UserFollowerResponseDTO;
import com.discuessit.userManagemnet.dto.UserRequestDTO;
import com.discuessit.userManagemnet.mapper.UserFollowerMapper;
import com.discuessit.userManagemnet.mapper.UserMapper;
import com.discuessit.userManagemnet.model.User;
import com.discuessit.userManagemnet.model.UserFollower;
import com.discuessit.userManagemnet.model.UserFollowerId;
import com.discuessit.userManagemnet.repository.UserFollowerRepository;
import com.discuessit.userManagemnet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public long registerUser(UserRequestDTO dto){
        User user = userMapper.toEntity(dto);
        userRepository.save(user);
        return user.getId();
    }

    public long login(String username,String password){
        User user = (User) userRepository.findByUsername(username);

        if (user==null) {
            throw new RuntimeException("Invalid username !!");
        }
        if(!user.getPassword().equals(password)){
            throw new RuntimeException("Invalid password !!");
        }
        return user.getId();
    }

    public void followUser(Long userId, Long followerId) {
        if (userId.equals(followerId)) {
            // i don't think this case can happen so i don't know what to do here :)
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Follower not found"));

        UserFollowerId id = new UserFollowerId(userId, followerId);

        boolean alreadyFollowing = userFollowerRepository.existsById(id);
        if (alreadyFollowing) {
            // the UI must not show a following button in this case so this case
            // and make it clear to the user that they are following this user
        }

        UserFollower userFollower = UserFollower.builder()
                .id(id)
                .user(user)
                .follower(follower)
                .build();

        userFollowerRepository.save(userFollower);
    }

    public List<UserFollowerResponseDTO> listFollowers(Long userId) {
        List<UserFollower> followers = userFollowerRepository.findByUserId(userId);
        return followers.stream()
                .map(userFollowerMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<UserFollowerResponseDTO> listFollowing(Long followerId) {
        List<UserFollower> following = userFollowerRepository.findByFollowerId(followerId);
        return following.stream()
                .map(userFollowerMapper::toDto)
                .collect(Collectors.toList());
    }

    public void unfollowUser(Long userId, Long followerId) {
        UserFollowerId id = new UserFollowerId(userId, followerId);

        boolean exists = userFollowerRepository.existsById(id);
        if (!exists) {
            throw new RuntimeException("Follow relationship does not exist.");
        }

        userFollowerRepository.deleteById(id);
    }
}
// i still have points logic, community logic

