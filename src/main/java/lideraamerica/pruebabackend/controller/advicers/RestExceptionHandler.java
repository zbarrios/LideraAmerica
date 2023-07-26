package lideraamerica.pruebabackend.controller.advicers;

import lideraamerica.pruebabackend.controller.responses.RestControllerErrorTemplate;
import lideraamerica.pruebabackend.exception.AuthorNotFoundException;
import lideraamerica.pruebabackend.exception.BookNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {AuthorNotFoundException.class})
  protected ResponseEntity<Object> handleDeviceNotFoundException() {
    String body = "There is no Author with the parameter passed. Please validate.";
    return new ResponseEntity<>(new RestControllerErrorTemplate(body, HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {BookNotFoundException.class})
  protected  ResponseEntity<Object> handleServiceNotFoundException() {
    String body = "There is no Book with the parameter passed. Please validate.";
    return new ResponseEntity<>(new RestControllerErrorTemplate(body, HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {ConstraintViolationException.class})
  protected  ResponseEntity<Object> handleIntegrityConstraintViolations(RuntimeException ex, WebRequest request) {
    String body = "There is a property passed as parameter that don't allow duplicates and already exists in the database, please rectify.";

    return handleExceptionInternal(ex, new RestControllerErrorTemplate(body, HttpStatus.BAD_REQUEST.value()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(value = {EmptyResultDataAccessException.class})
  protected ResponseEntity<Object> handleEmptyResultException(RuntimeException ex, WebRequest request){
    String body = "Couldn't find the object requested by the ID passed as parameter, please check.";
    return handleExceptionInternal(ex, new RestControllerErrorTemplate(body, HttpStatus.NOT_FOUND.value()), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }





}
