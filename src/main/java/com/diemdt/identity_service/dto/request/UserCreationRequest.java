package com.diemdt.identity_service.dto.request;

import com.diemdt.identity_service.exception.ErrorCode;
import com.diemdt.identity_service.validator.DobConstraint;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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

    @DobConstraint(min = 18)
    private LocalDate dob ;
    List<String> roles ;
}
