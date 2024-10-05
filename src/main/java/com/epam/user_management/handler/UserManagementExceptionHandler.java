package com.epam.user_management.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.epam.user_management.exception.UserException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestControllerAdvice
@Slf4j
public class UserManagementExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail methodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        ProblemDetail problemDetail = ProblemDetail.
                forStatusAndDetail(HttpStatus.BAD_REQUEST, "Request is invalid");
        Map<String,Object> errorMap = errors.stream().collect(Collectors.toMap(x->x.getField(), x->x.getDefaultMessage()));
        problemDetail.setProperties(errorMap);
        return problemDetail;

    }

    @ExceptionHandler(UserException.class)
    public ProblemDetail userException(UserException userException){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(userException.getHttpStatus(),"User Exception");
        problemDetail.setTitle(userException.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail invlalidType(HttpMessageNotReadableException ex){
        ProblemDetail problemDetail=ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,"Invalid input");
        problemDetail.setTitle(ex.getMessage());
        return problemDetail;

    }

//    @ExceptionHandler(UserAccessDenied.class)
//    public ProblemDetail userAccessDenied(UserAccessDenied userAccessDenied){
//        ProblemDetail problemDetail=ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN,"User Exception");
//        problemDetail.setTitle(userAccessDenied.getMessage());
//        return problemDetail;
//    }





}
