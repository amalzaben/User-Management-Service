package com.discuessit.userManagemnet.mapper.dto.controllerDTO;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank String username,
        @NotBlank String password
) {
}
