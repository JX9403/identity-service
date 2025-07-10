package com.diemdt.identity_service.mapper;

import com.diemdt.identity_service.dto.request.PermissionRequest;
import com.diemdt.identity_service.dto.response.PermissionResponse;
import com.diemdt.identity_service.entity.Permission;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toEntity(PermissionRequest request);

    PermissionResponse toDTO(Permission permission);
}