package com.diemdt.identity_service.dto.response;

import com.diemdt.identity_service.enums.Role;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String id ;
    private String username ;
    private String firstName ;
    private String lastName ;
    private LocalDate dob ;

    private Set<RoleResponse> roles ;
}
