package com.diemdt.identity_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
    INVALID_KEY(1000, "Invalid message validation"),
    USERNAME_INVALID(1002, "Username must be at least 3 characters"),
    PASSWORD_INVALID(1003, "Password must be at least 8 characters"),
    USER_EXISTS_EXCEPTION(1001, "User exists"),
    UNAUTHENTICATED(1005, "Unauthenticated"),
    USER_NOT_EXISTS_EXCEPTION(1004, "User not found");

    private int code;
    private String message ;
}
