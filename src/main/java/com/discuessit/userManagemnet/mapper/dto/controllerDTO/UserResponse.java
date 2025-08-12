package com.discuessit.userManagemnet.mapper.dto.controllerDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserResponse(
        @NotBlank long id,
        @NotBlank String username,
        @Email String email
) {
}
