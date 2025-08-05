package com.discuessit.userManagemnet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_follower")
public class UserFollower {

    @EmbeddedId
    private UserFollowerId id;

    @ManyToOne
    @MapsId("user")  // maps to the field in UserFollowerId
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("follower")  // maps to the field in UserFollowerId
    @JoinColumn(name = "follower_id")
    private User follower;
}

