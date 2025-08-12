package com.discuessit.userManagemnet.mapper.dto.controllerDTO;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record RegisterUserRequest(
        @NotBlank String username,
        @NotBlank String password,
        @Email String email
) { }
