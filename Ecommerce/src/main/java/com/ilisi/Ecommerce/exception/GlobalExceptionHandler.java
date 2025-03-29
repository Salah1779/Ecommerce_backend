package com.ilisi.Ecommerce.exception;

import com.ilisi.Ecommerce.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Response> handleProductNotFound(ProductNotFoundException ex) {
        Response res= new Response(false , HttpStatus.NOT_FOUND , ex.getMessage() , null);
        return new ResponseEntity<Response>(res, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Response> handleCategoryNotFound(CategoryNotFoundException ex) {
        Response res= new Response(false , HttpStatus.NOT_FOUND , ex.getMessage() , null);
        return new ResponseEntity<Response>(res, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response> handleIllegalArgument(IllegalArgumentException ex) {
        Response res= new Response(false , HttpStatus.BAD_REQUEST , ex.getMessage() , null);
        return new ResponseEntity<Response>(res, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGenericException(Exception ex) {
        Response res= new Response(false , HttpStatus.INTERNAL_SERVER_ERROR , ex.getMessage() , null);
        return new ResponseEntity<Response>(res,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}