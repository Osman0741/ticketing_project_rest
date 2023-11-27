package com.cydeo.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ExceptionWrapper {

    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime localDateTime;

    private Integer errorCount;
    private List<ValidationException> validationExceptionList;




    public ExceptionWrapper(String message, HttpStatus httpStatus){
        this.message=message;
        this.httpStatus=httpStatus;
        this.localDateTime =LocalDateTime.now();
    }
}
