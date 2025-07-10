package com.diemdt.identity_service.mapper;

import com.diemdt.identity_service.dto.request.UserCreationRequest;
import com.diemdt.identity_service.dto.request.UserUpdateRequest;
import com.diemdt.identity_service.dto.response.UserResponse;
import com.diemdt.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserCreationRequest request);


    UserResponse toDTO(User user);

    @Mapping(target = "roles", ignore = true)
    void update(@MappingTarget User user, UserUpdateRequest request);
}