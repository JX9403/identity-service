package com.diemdt.identity_service.dto.response;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String id ;
    private String username ;
    private String password ;
    private String firstName ;
    private String lastName ;
    private LocalDate dob ;
}
