package com.warungmakanbahari.warungmakanbahari.shared.configs;

import com.warungmakanbahari.warungmakanbahari.shared.dtos.ErrorResponse;
import com.warungmakanbahari.warungmakanbahari.shared.exceptions.DataExistException;
import com.warungmakanbahari.warungmakanbahari.shared.exceptions.NotFoundException;
import com.warungmakanbahari.warungmakanbahari.shared.exceptions.UnauthorizedException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleErrorException(NotFoundException e) {
        ErrorResponse response = new ErrorResponse<>(
                String.valueOf(HttpStatus.NOT_FOUND.value()), "Data Not Found", e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DataExistException.class)
    public ResponseEntity<ErrorResponse> handleErrorException(DataExistException e) {
        ErrorResponse response = new ErrorResponse<>(
                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                "Duplicate Data",
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnautorizedException(UnauthorizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ErrorResponse<>(
                        String.valueOf(HttpStatus.UNAUTHORIZED.value()),
                        "Unauthorized",
                        e.getMessage()
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse<List<String>>> handleMethodArgument(MethodArgumentNotValidException e) {
        List<String> fieldErrors = e.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        ErrorResponse<List<String>> response = new ErrorResponse<>(
                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                fieldErrors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
