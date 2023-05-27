package br.com.planilha.leitorapp.application.exception;

import br.com.planilha.leitorapp.domain.city.exception.CityException;
import br.com.planilha.leitorapp.domain.spreadsheet.exception.SpreadsheetException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({SpreadsheetException.class, CityException.class})
    public ResponseEntity<Object> handleInternalServerErrorException(Exception ex) {
        return new ResponseEntity<>(
                new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, List.of(ex.getMessage())),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
