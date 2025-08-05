package com.discuessit.userManagemnet.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFollowerId implements Serializable {
    private Long user;      // This maps to user_id
    private Long follower;  // This maps to follower_id
}
