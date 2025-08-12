package com.discuessit.userManagemnet.mapper.dto.controllerDTO;

import jakarta.validation.constraints.NotNull;

public record UserFollowerRequest(
        @NotNull Long followedUserId,
        @NotNull Long followerId
) {
}
