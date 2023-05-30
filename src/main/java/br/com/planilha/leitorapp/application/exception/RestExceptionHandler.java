package br.com.planilha.leitorapp.application.exception;

import br.com.planilha.leitorapp.domain.city.exception.CityException;
import br.com.planilha.leitorapp.domain.city.exception.CityNotFoundException;
import br.com.planilha.leitorapp.domain.district.exception.DistrictException;
import br.com.planilha.leitorapp.domain.region.exception.RegionException;
import br.com.planilha.leitorapp.domain.region.exception.RegionNotFoundException;
import br.com.planilha.leitorapp.domain.spreadsheet.exception.SpreadsheetException;
import br.com.planilha.leitorapp.domain.spreadsheet.exception.SpreadsheetFormatInvalidException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

import static java.util.stream.Collectors.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@AllArgsConstructor
public class RestExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler({SpreadsheetException.class, CityException.class, RegionException.class, DistrictException.class})
    public ResponseEntity<Object> handleInternalServerErrorException(Exception ex) {
        return new ResponseEntity<>(new ApiError(INTERNAL_SERVER_ERROR, List.of(ex.getMessage())), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({CityNotFoundException.class, SpreadsheetFormatInvalidException.class, RegionNotFoundException.class})
    public ResponseEntity<Object> handleBadRequestException(Exception ex) {
        return new ResponseEntity<>(new ApiError(BAD_REQUEST, List.of(ex.getMessage())), BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handle(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errorsByField = ex.getBindingResult().getFieldErrors().stream()
                .collect(groupingBy(FieldError::getField, LinkedHashMap::new,
                        mapping(e -> e.getField() + ": " + messageSource.getMessage(e, LocaleContextHolder.getLocale()), toList())));

        List<String> errors = errorsByField.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .flatMap(entry -> entry.getValue().stream().sorted())
                .toList();

        return new ResponseEntity<>(new ApiError(BAD_REQUEST, errors), BAD_REQUEST);
    }
}
