package ru.iteco.account.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.iteco.account.model.dto.ErrorDto;
import ru.iteco.account.model.exception.TransactionException;

@RestControllerAdvice
public class TransactionRestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TransactionException.class)
    public ErrorDto handleTransactionException(TransactionException exception) {
        return new ErrorDto(HttpStatus.BAD_REQUEST.name(), exception.getMessage());
    }
}
