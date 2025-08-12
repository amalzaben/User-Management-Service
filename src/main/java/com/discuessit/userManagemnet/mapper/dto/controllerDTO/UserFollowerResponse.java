package com.discuessit.userManagemnet.mapper.dto.controllerDTO;

import jakarta.validation.constraints.NotNull;

public record UserFollowerResponse(
        @NotNull Long followedUserId,
        @NotNull Long followerId) {
}
