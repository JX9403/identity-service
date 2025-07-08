package com.diemdt.identity_service.controller;

import com.diemdt.identity_service.dto.request.AuthenticationRequest;
import com.diemdt.identity_service.dto.request.IntrospectRequest;
import com.diemdt.identity_service.dto.response.ApiResponse;
import com.diemdt.identity_service.dto.response.AuthenticationResponse;
import com.diemdt.identity_service.dto.response.IntrospectResponse;
import com.diemdt.identity_service.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService ;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request){
        AuthenticationResponse res = authenticationService.authenticate(request);
     return ApiResponse.<AuthenticationResponse>builder()
             .result(res)
             .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate (@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var res = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(res)
                .build();
    }
}
