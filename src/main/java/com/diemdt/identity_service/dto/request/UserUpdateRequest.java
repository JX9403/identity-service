package com.diemdt.identity_service.dto.request;

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
public class UserUpdateRequest {
    @Size(min= 8, message = "PASSWORD_INVALID")
    private String password ;
    private String firstName ;
    private String lastName ;
    private LocalDate dob ;

    List<String> roles ;

}
