package com.diemdt.identity_service.exception;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppException extends RuntimeException{

    private ErrorCode errorCode ;

}
