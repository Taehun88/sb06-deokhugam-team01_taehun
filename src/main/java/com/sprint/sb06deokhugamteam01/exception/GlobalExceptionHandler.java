package com.sprint.sb06deokhugamteam01.exception;

import com.sprint.sb06deokhugamteam01.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception exception) {
        log.error("예상치 못한 오류 발생: {}", exception.getMessage(), exception);
        return ErrorDto.toResponseEntity(exception);
    }

    @ExceptionHandler(RootException.class)
    public ResponseEntity<ErrorDto> handleRootException(RootException exception){
        log.error("커스텀 예외 발생: code={}, message={}", exception.getErrorCode(), exception.getMessage());
        return ErrorDto.toResponseEntity(exception);
    }
}
