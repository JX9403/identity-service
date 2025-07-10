package com.diemdt.identity_service.controller;

import com.diemdt.identity_service.dto.request.PermissionRequest;
import com.diemdt.identity_service.dto.response.ApiResponse;
import com.diemdt.identity_service.dto.response.PermissionResponse;
import com.diemdt.identity_service.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
public class PermissionController {
    public final PermissionService permissionService ;

    @PostMapping
    ApiResponse<PermissionResponse> create (@RequestBody PermissionRequest request){
        return ApiResponse.<PermissionResponse>builder().result( permissionService.create(request)).build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> findAll(){
        return ApiResponse.<List<PermissionResponse>>builder().result(permissionService.getAll()).build();
    }

    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable(value = "permission") String permission){
        permissionService.delete(permission);

        return ApiResponse.<Void>builder().build();
    }
}
