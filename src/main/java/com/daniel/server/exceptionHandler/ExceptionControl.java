package com.daniel.server.exceptionHandler;

import com.daniel.server.dto.ServerErrorData;
import com.daniel.server.exceptions.ServerException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ExceptionControl extends ResponseEntityExceptionHandler {

@ExceptionHandler(value = {ServerException.class})
@ResponseBody
public ResponseEntity<Object> exceptionResponse(Exception e, WebRequest webRequest) {
    ServerErrorData generalError = new ServerErrorData(601, "General Error", "Please contact your administrator");
    e.printStackTrace();

    if (e instanceof ServerException) {
        int errorCode = ((ServerException) e).getErrorType().getErrorNumber();
        String errorMessage = ((ServerException) e).getErrorType().getErrorMessage();
        String errorType = String.valueOf(((ServerException) e).getErrorType());
        generalError = new ServerErrorData(errorCode, errorMessage, errorType);
        return handleExceptionInternal(e, generalError, new HttpHeaders(), HttpStatus.BAD_GATEWAY, webRequest);
    }
    return handleExceptionInternal(e, generalError, new HttpHeaders(), HttpStatus.BAD_GATEWAY, webRequest);

}
}