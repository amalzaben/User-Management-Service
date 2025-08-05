package com.discuessit.userManagemnet.dto;

import lombok.Data;

@Data
public class UserFollowerRequestDTO {
    private Long userId;
    private Long followerId;
}
