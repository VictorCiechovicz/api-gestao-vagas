package com.gestaovagas.modules.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerControllers {

    private MessageSource messageSource;


    //criado para nao iniciar o messageSource como null
    public ExceptionHandlerControllers(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorDTO>> handleException(MethodArgumentNotValidException e) {
        List<ErrorDTO> dto = new ArrayList<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            dto.add(new ErrorDTO(message, error.getField()));

        }

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }


}
