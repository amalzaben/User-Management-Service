package com.discuessit.userManagemnet.mapper;

import com.discuessit.userManagemnet.dto.UserFollowerRequestDTO;
import com.discuessit.userManagemnet.dto.UserFollowerResponseDTO;
import com.discuessit.userManagemnet.model.UserFollower;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserFollowerMapper {

    @Mapping(source = "id.user", target = "userId")
    @Mapping(source = "id.follower", target = "followerId")
    UserFollowerResponseDTO toDto(UserFollower userFollower);

    @Mapping(source = "userId", target = "id.user")
    @Mapping(source = "followerId", target = "id.follower")
    UserFollower toEntity(UserFollowerRequestDTO dto);
}
