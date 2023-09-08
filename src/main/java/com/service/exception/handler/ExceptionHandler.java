package com.service.exception.handler;

import com.jwt.server.exception.SecurityException;
import com.service.exception.ErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandler {

    private static final String COMMA = ", ";
    private static final String CHAR = "'";


    @org.springframework.web.bind.annotation.ExceptionHandler(SecurityException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse securityException(SecurityException ex) {
        return buildResponse(ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = Arrays.stream(Objects.requireNonNull(ex.getDetailMessageArguments()))
                .filter(o -> o instanceof ArrayList<?>)
                .map(o -> (ArrayList<?>) o)
                .flatMap(Collection::stream)
                .map(Object::toString)
                .map(s -> s.replace(CHAR, StringUtils.EMPTY))
                .collect(Collectors.joining(COMMA));

        return buildResponse(errorMessage);
    }

    private ErrorResponse buildResponse(String name) {
        return new ErrorResponse(name, LocalDateTime.now());
    }
}
