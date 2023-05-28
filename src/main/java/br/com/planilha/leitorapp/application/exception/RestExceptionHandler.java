package br.com.planilha.leitorapp.application.exception;

import br.com.planilha.leitorapp.domain.city.exception.CityException;
import br.com.planilha.leitorapp.domain.city.exception.CityNotFoundException;
import br.com.planilha.leitorapp.domain.spreadsheet.exception.SpreadsheetException;
import br.com.planilha.leitorapp.domain.spreadsheet.exception.SpreadsheetFormatInvalidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({SpreadsheetException.class, CityException.class})
    public ResponseEntity<Object> handleInternalServerErrorException(Exception ex) {
        return new ResponseEntity<>(new ApiError(INTERNAL_SERVER_ERROR, List.of(ex.getMessage())), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({CityNotFoundException.class, SpreadsheetFormatInvalidException.class})
    public ResponseEntity<Object> handleBadRequestException(Exception ex) {
        return new ResponseEntity<>(new ApiError(BAD_REQUEST, List.of(ex.getMessage())), BAD_REQUEST);
    }
}
