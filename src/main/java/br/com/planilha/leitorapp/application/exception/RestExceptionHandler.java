package br.com.planilha.leitorapp.application.exception;

import br.com.planilha.leitorapp.domain.spreadsheet.exception.SpreadsheetException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(SpreadsheetException.class)
    public ResponseEntity<Object> handleSpreadsheetExceptionException(Exception ex) {
        return new ResponseEntity<>(
                new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, List.of("Erro interno")),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
