package com.discuessit.userManagemnet.mapper;

import com.discuessit.userManagemnet.dto.UserRequestDTO;
import com.discuessit.userManagemnet.dto.UserResponseDTO;
import com.discuessit.userManagemnet.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDTO toDto(User user);

    User toEntity(UserRequestDTO dto);

    void updateEntityFromDto(UserRequestDTO dto, @MappingTarget User user);
}
