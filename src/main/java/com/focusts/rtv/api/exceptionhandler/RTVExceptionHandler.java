package com.focusts.rtv.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RTVExceptionHandler extends ResponseEntityExceptionHandler {
    
    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {        
        String userMessage = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String devMessage = Optional.ofNullable(ex.getCause()).orElse(ex).toString();

        List<RtvError> errors = Arrays.asList(new RtvError(userMessage, devMessage));
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<RtvError> errors = CreateListOfRtvError(ex.getBindingResult());
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    private List<RtvError> CreateListOfRtvError(BindingResult bindingResult){
        List<RtvError> errors = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()){
            String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String devMessage = fieldError.toString();
            errors.add(new RtvError(userMessage, devMessage));
        }

        return errors;
    }

    @ExceptionHandler({ EmptyResultDataAccessException.class })
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){
        String userMessage = messageSource.getMessage("recurso.noa-econtrado", null, LocaleContextHolder.getLocale());
        String devMessage = ex.toString();
        List<RtvError> erros = Arrays.asList(new RtvError(userMessage, devMessage));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
        String menagemUsuario = messageSource.getMessage("recurso.operacao-nao-permitida", null, LocaleContextHolder.getLocale());
        String menagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<RtvError> erros = Arrays.asList(new RtvError(menagemUsuario, menagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }    

}
