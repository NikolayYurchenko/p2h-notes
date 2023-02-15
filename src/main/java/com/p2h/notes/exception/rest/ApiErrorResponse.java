package com.p2h.notes.exception.rest;

import com.p2h.notes.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {

    private int code;

    private Long timestamp;

    private String message;

    private Map<String, String> errors = new HashMap<>();

    private final static int defaultErrorCode = 500;

    /**
     * Build default system exception
     * @return
     */
    protected static ApiErrorResponse buildDefault() {

        ApiErrorResponse response = new ApiErrorResponse();

        response.setCode(defaultErrorCode);
        response.setTimestamp(System.currentTimeMillis());

        return response;
    }

    /**
     * Build constraint validation api response
     * @param e
     * @return
     */
    protected static ApiErrorResponse buildForConstraintViolation(ConstraintViolationException e) {

        ApiErrorResponse response = new ApiErrorResponse();

        response.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        response.setTimestamp(System.currentTimeMillis());
        response.setMessage(e.getMessage());

        e.getConstraintViolations().forEach(it -> {
            response.getErrors().put(it.getPropertyPath().toString(), it.getMessage());
        });

        return response;
    }

    /**
     * Build api response for system global exception
     * @param e
     * @return
     */
    protected static ApiErrorResponse buildForGlobalException(Exception e) {

        ApiErrorResponse response = new ApiErrorResponse();

        response.setMessage(e.getMessage());
        response.setTimestamp(System.currentTimeMillis());
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

        if (e instanceof BaseException) {
            response.setCode(((BaseException) e).getCode());
        } else if (e.getCause() instanceof BaseException) {
            response.setCode(((BaseException) e.getCause()).getCode());
        }

        return response;
    }
}
