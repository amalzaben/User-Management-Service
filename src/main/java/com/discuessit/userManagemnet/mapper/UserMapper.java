package com.discuessit.userManagemnet.mapper;

import com.discuessit.userManagemnet.mapper.dto.serviceDTO.RegisterUserCommand;
import com.discuessit.userManagemnet.mapper.dto.controllerDTO.RegisterUserRequest;
import com.discuessit.userManagemnet.mapper.dto.controllerDTO.UserResponse;
import com.discuessit.userManagemnet.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toDto(User user);

    User toEntity(RegisterUserCommand command);

    void updateEntityFromDto(RegisterUserRequest request, @MappingTarget User user);
}
