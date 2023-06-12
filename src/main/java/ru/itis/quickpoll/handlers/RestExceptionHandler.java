package ru.itis.quickpoll.handlers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itis.quickpoll.dto.error.ErrorDetail;
import ru.itis.quickpoll.dto.error.ValidationError;
import ru.itis.quickpoll.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {
    @Autowired
    private MessageSource messageSource;
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Resource Not Found");
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setDeveloperMessage(ex.getClass().getName());
        return new ResponseEntity<>(errorDetail, null, HttpStatus.
                NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationError(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestPath == null) {
            requestPath = request.getRequestURI();
        }
        ErrorDetail errorDetail = ErrorDetail.builder()
                .timeStamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Validation failed")
                .detail("Input validation failed")
                .developerMessage(ex.getClass().getName())
                .path(requestPath)
                .build();

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        for (FieldError fe : fieldErrors) {
            List<ValidationError> validationErrorList = errorDetail.getErrors().get(fe.getField());

            if (validationErrorList == null) {
                validationErrorList = new ArrayList<>();
                errorDetail.getErrors().put(fe.getField(), validationErrorList);
            }

            ValidationError validationError = new ValidationError();
            validationError.setCode(fe.getCode());
            validationError.setMessage(messageSource.getMessage(fe,null));
            validationErrorList.add(validationError);
        }
        return new ResponseEntity<>(errorDetail, null, HttpStatus.BAD_REQUEST);
    }
}

