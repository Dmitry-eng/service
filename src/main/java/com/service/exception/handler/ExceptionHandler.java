package com.service.exception.handler;

import com.jwt.server.exception.SecurityException;
import com.service.exception.ErrorResponse;
import com.service.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ExceptionHandler {

    private static final String COMMA = ", ";
    private static final String CHAR = "'";
    private static final String DEFAULT_ERROR_MESSAGE = "Неизвестная ошибка, обратитесь к администратору системы.";


    @org.springframework.web.bind.annotation.ExceptionHandler(SecurityException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse securityException(SecurityException ex) {
        return buildResponse(ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse serviceException(ServiceException ex) {
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

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse defaultException(Exception ex) {
        log.error(DEFAULT_ERROR_MESSAGE, ex);
        return buildResponse(DEFAULT_ERROR_MESSAGE);
    }

    private ErrorResponse buildResponse(String name) {
        return new ErrorResponse(name, LocalDateTime.now());
    }
}
