package com.discuessit.userManagemnet.controller;

import com.discuessit.userManagemnet.mapper.dto.controllerDTO.*;
import com.discuessit.userManagemnet.mapper.dto.serviceDTO.RegisterUserCommand;
import com.discuessit.userManagemnet.mapper.UserMapper;
import com.discuessit.userManagemnet.model.User;
import com.discuessit.userManagemnet.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    private User user;
    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        RegisterUserCommand command = new RegisterUserCommand(
                request.username(),
                request.password(),
                request.email()
        );
        User createdUser = userService.registerUser(command);
        UserResponse userResponse=userMapper.toDto(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED)//check
                .body(userResponse);
    }

    // Login user
    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@Valid@RequestBody LoginRequest loginRequest) {
        User user = userService.login(loginRequest.username(), loginRequest.password());

        UserResponse response = userMapper.toDto(user);

        return ResponseEntity.ok(response);
    }

    // Follow a user
    @PostMapping(value = "{followerId}/follow/{followedUserId}")
    public ResponseEntity<Void> followUser(@PathVariable Long followedUserId, @PathVariable Long followerId) {
        userService.followUser(followedUserId, followerId);
        return ResponseEntity.ok().build();
    }

    // unfollow user
    @DeleteMapping("/{followerId}/unfollow/{followedUserId}")
    public ResponseEntity<Void> unfollowUser(@PathVariable Long followedUserId, @PathVariable Long followerId) {
        userService.unfollowUser(followedUserId, followerId);
        return ResponseEntity.noContent().build();
    }

    // List followers of a user
    @GetMapping("/{userId}/followers")
    public ResponseEntity<PaginatedResponse<UserFollowerResponse>> listFollowers(
            @PathVariable Long userId,
            @PageableDefault(size = 10) Pageable pageable) {
        PaginatedResponse<UserFollowerResponse> response = userService.listFollowers(userId, pageable);
        return ResponseEntity.ok(response);
    }

    // List users that the user is following
    @GetMapping("/{userId}/following")
    public ResponseEntity<PaginatedResponse<UserFollowerResponse>> getFollowing(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        PaginatedResponse<UserFollowerResponse> response = userService.listFollowing(userId, pageable);

        return ResponseEntity.ok(response);
    }


}
