package com.discuessit.userManagemnet.mapper;

import com.discuessit.userManagemnet.mapper.dto.controllerDTO.UserFollowerRequest;
import com.discuessit.userManagemnet.mapper.dto.controllerDTO.UserFollowerResponse;
import com.discuessit.userManagemnet.model.UserFollower;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserFollowerMapper {

    @Mapping(source = "id.user", target = "followedUserId")
    @Mapping(source = "id.follower", target = "followerId")
    UserFollowerResponse toDto(UserFollower userFollower);

    @Mapping(source = "followedUserId", target = "id.user")
    @Mapping(source = "followerId", target = "id.follower")
    UserFollower toEntity(UserFollowerRequest dto);
}
