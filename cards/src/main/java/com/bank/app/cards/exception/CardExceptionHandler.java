package com.bank.app.cards.exception;

import com.bank.app.cards.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CardExceptionHandler {

    @ExceptionHandler(CardAlreadyExistsException.class)
    public final ErrorResponseDto handleCardAlreadyExistsException(CardAlreadyExistsException ex,
            WebRequest webRequest) {
        return new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.BAD_REQUEST,
                ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ErrorResponseDto handleMethodArgumentInvalidException(MethodArgumentNotValidException ex,
            WebRequest webRequest) {
        return new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.BAD_REQUEST,
                ex.getFieldErrors().stream().map(err -> err.getField() + ": " + err.getDefaultMessage())
                        .collect(Collectors.joining(", ")), LocalDateTime.now());
    }


    @ExceptionHandler(Exception.class)
    public final ErrorResponseDto handleGeneralException(Exception ex, WebRequest webRequest) {
        return new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.BAD_REQUEST, ex.getMessage(),
                LocalDateTime.now());
    }
}
