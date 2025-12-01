package com.sprint.sb06deokhugamteam01.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sprint.sb06deokhugamteam01.dto.ErrorDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RootException.class)
    public ResponseEntity<ErrorDto> handleRootException(RootException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        log.warn("Handled custom exception: {}", ex.getMessage(), ex);

        return ResponseEntity.status(errorCode.getStatus())
                .body(ErrorDto.builder()
                        .message(errorCode.getMessage())
                        .status(errorCode.getStatus())
                        .code(errorCode.getCode())
                        .details(ex.getDetails())
                        .timestamp(ex.getTimestamp())
                        .exceptionType(ex.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGeneralException(Exception ex) {
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        log.error("Unhandled exception", ex);

        return ResponseEntity.status(errorCode.getStatus())
                .body(ErrorDto.builder()
                        .message(errorCode.getMessage())
                        .status(errorCode.getStatus())
                        .code(errorCode.getCode())
                        .details(Map.of())
                        .timestamp(LocalDateTime.now())
                        .exceptionType(ex.getClass().getSimpleName())
                        .build());
    }

}
