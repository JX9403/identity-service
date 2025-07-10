package com.diemdt.identity_service.service;

import com.diemdt.identity_service.dto.request.RoleRequest;
import com.diemdt.identity_service.dto.response.RoleResponse;
import com.diemdt.identity_service.mapper.RoleMapper;
import com.diemdt.identity_service.repository.PermissionRepository;
import com.diemdt.identity_service.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    public final RoleMapper roleMapper ;
    public final RoleRepository roleRepository ;
    private final PermissionRepository permissionRepository;

    public RoleResponse create (RoleRequest request){
        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role) ;

        return roleMapper.toRoleResponse(role) ;
    }


    public List<RoleResponse> getAll() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
