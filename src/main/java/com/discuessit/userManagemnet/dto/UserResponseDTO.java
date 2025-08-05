package com.discuessit.userManagemnet.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private Long points;
    private boolean loggedIn;
}
