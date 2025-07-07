package com.diemdt.identity_service.controller;

import com.diemdt.identity_service.dto.request.AuthenticationRequest;
import com.diemdt.identity_service.dto.response.ApiResponse;
import com.diemdt.identity_service.dto.response.AuthenticationResponse;
import com.diemdt.identity_service.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService ;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request){
     boolean res = authenticationService.authenticate(request);
     return ApiResponse.<AuthenticationResponse>builder().code(200).result(AuthenticationResponse.builder().authenticated(res).build())
             .build();
    }
}
