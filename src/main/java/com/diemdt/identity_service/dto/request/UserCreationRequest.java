package com.diemdt.identity_service.dto.request;

import com.diemdt.identity_service.exception.ErrorCode;
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
public class UserCreationRequest {

    @Size(min= 3, message = "USERNAME_INVALID")
    private String username ;
    @Size(min= 8, message = "PASSWORD_INVALID")
    private String password ;
    private String firstName ;
    private String lastName ;
    private LocalDate dob ;
}
