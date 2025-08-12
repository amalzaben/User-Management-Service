package com.discuessit.userManagemnet.mapper.dto.serviceDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserCommand(
        @NotBlank String username,
        @NotBlank String password,
        @Email String email
) { }
