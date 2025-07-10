package com.diemdt.identity_service.service;

import com.diemdt.identity_service.dto.request.PermissionRequest;
import com.diemdt.identity_service.dto.response.PermissionResponse;
import com.diemdt.identity_service.entity.Permission;


import com.diemdt.identity_service.mapper.PermissionMapper;
import com.diemdt.identity_service.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionService {
    public final PermissionRepository permissionRepository ;

    public  final PermissionMapper permissionMapper ;
    public PermissionResponse create (PermissionRequest request){
        Permission permission = permissionMapper.toEntity(request);

        permission = permissionRepository.save(permission);

        return permissionMapper.toDTO(permission);
    }

    public List<PermissionResponse> getAll(){
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toDTO).collect(Collectors.toList()) ;
    }

  public  void delete (String permission){
        permissionRepository.deleteById(permission);
    }
}
