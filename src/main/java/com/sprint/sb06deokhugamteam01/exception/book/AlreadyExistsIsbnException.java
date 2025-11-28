package com.sprint.sb06deokhugamteam01.exception.book;

import com.sprint.sb06deokhugamteam01.exception.ErrorCode;
import com.sprint.sb06deokhugamteam01.exception.RootException;

import java.util.Map;

public class AlreadyExistsIsbnException extends RootException {

    ErrorCode errorCode = ErrorCode.ALREADY_EXISTS_ISBN;

    public AlreadyExistsIsbnException(Map<String, Object> details) {
        super(ErrorCode.ALREADY_EXISTS_ISBN, details);
    }
}
