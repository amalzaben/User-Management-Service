package com.discuessit.userManagemnet.controller;

import com.discuessit.userManagemnet.dto.UserFollowerResponseDTO;
import com.discuessit.userManagemnet.dto.UserRequestDTO;
import com.discuessit.userManagemnet.repository.UserRepository;
import com.discuessit.userManagemnet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<Long> registerUser(@RequestBody UserRequestDTO dto) {
        long userId = userService.registerUser(dto);
        return ResponseEntity.ok(userId);
    }

    // Login user
    @PostMapping("/login")
    public ResponseEntity<Long> login(@RequestParam String username, @RequestParam String password) {
        long login = userService.login(username, password);
        long userId = login;
        return ResponseEntity.ok(userId);
    }

    // Follow a user
    @PostMapping("/{followerId}/follow/{userId}")
    public ResponseEntity<Void> followUser(@PathVariable Long userId, @PathVariable Long followerId) {
        userService.followUser(userId, followerId);
        return ResponseEntity.ok().build();
    }

    // List followers of a user
    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<UserFollowerResponseDTO>> listFollowers(@PathVariable Long userId) {
        List<UserFollowerResponseDTO> followers = userService.listFollowers(userId);
        return ResponseEntity.ok(followers);
    }

    // List users that the user is following
    @GetMapping("/{followerId}/following")
    public ResponseEntity<List<UserFollowerResponseDTO>> listFollowing(@PathVariable Long followerId) {
        List<UserFollowerResponseDTO> following = userService.listFollowing(followerId);
        return ResponseEntity.ok(following);
    }
    // unfollow user
    @DeleteMapping("/unfollow")
    public ResponseEntity<String> unfollowUser(@RequestParam Long userId, @RequestParam Long followerId) {
        userService.unfollowUser(userId, followerId);
        return ResponseEntity.ok(followerId+" Unfollowed "+userId+" successfully");
    }

}
