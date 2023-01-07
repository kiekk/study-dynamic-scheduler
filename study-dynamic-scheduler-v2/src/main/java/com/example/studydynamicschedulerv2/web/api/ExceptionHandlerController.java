package com.example.studydynamicschedulerv2.web.api;

import com.example.studydynamicschedulerv2.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerController implements ErrorController {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<Map<String, String>> handleException(ApiException exception) {
        Map<String, String> result = new HashMap();
        String message = exception.getMessage();
        if (null != exception.getArgs()) {
            for (int index = 0; index < exception.getArgs().length; ++index) {
                String arg = exception.getArgs()[index];
                message = message.replace("#" + index, arg);
            }
        }

        result.put("exception", exception.getType().name());
        result.put("message", message);
        return new ResponseEntity(result, exception.getType().getStatus());
    }

}
