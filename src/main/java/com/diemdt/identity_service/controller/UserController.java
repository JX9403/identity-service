package com.diemdt.identity_service.controller;

import com.diemdt.identity_service.dto.request.UserCreationRequest;
import com.diemdt.identity_service.dto.request.UserUpdateRequest;
import com.diemdt.identity_service.dto.response.ApiResponse;
import com.diemdt.identity_service.dto.response.UserResponse;
import com.diemdt.identity_service.entity.User;
import com.diemdt.identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService ;

    @PostMapping
    ApiResponse<User> create (@RequestBody @Valid  UserCreationRequest request){
        ApiResponse apiResponse = new ApiResponse<>();

        apiResponse.setCode(201);
        apiResponse.setResult(userService.create(request));

       return  apiResponse;

    }

    @GetMapping
    public ApiResponse<Page<UserResponse>> findAll(@PageableDefault(size = 10)Pageable pageable){
        ApiResponse apiResponse = new ApiResponse<>();

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        apiResponse.setCode(200);
        apiResponse.setResult(userService.findAll(pageable));

        return apiResponse;
    }


    @GetMapping("/{id}")
    ApiResponse<UserResponse> findById (@PathVariable("id") String id){
        ApiResponse apiResponse = new ApiResponse<>();

        apiResponse.setCode(200);
        apiResponse.setResult(userService.findById(id));

        return  apiResponse;

    }

    @PutMapping("/{id}")
   ApiResponse<UserResponse> update (@PathVariable("id") String id, @RequestBody UserUpdateRequest request){
        ApiResponse apiResponse = new ApiResponse<>();

        apiResponse.setCode(200);
        apiResponse.setResult(userService.update(id, request));

        return apiResponse;
    }

    @DeleteMapping("/{id}")
    String deleteById (@PathVariable("id") String id) {
        userService.deleteById(id);
        return "Delete successfully";
    }

}
