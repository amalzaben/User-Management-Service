package com.discuessit.userManagemnet.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String email;
    private long points;

//===== i assumed the user is logged in but keeping this for later =====
//    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
//    private boolean loggedIn = false;


}
