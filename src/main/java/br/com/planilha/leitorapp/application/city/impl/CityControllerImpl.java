package br.com.planilha.leitorapp.application.city.impl;

import br.com.planilha.leitorapp.application.city.CityController;
import br.com.planilha.leitorapp.domain.city.City;
import br.com.planilha.leitorapp.domain.city.CityService;
import br.com.planilha.leitorapp.domain.spreadsheet.SpreadsheetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Slf4j
@RestController
@RequestMapping("api/v1/cidades/")
@AllArgsConstructor
public class CityControllerImpl implements CityController {

    private final SpreadsheetService spreadsheetService;
    private final CityService cityService;

    @Override
    @PostMapping(value = "upload", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<City>> saveCities(@RequestParam("spreadsheet") MultipartFile spreadsheet) {
        log.info("Requisição Rest: upload recebida");
        List<City> cities = spreadsheetService.converter(spreadsheet);
        log.info("Inserindo cidades");
        cities = cityService.saveAll(cities);
        log.info("Cidades inseridas com sucesso!");
        return ResponseEntity.ok(cities);
    }

    @Override
    @GetMapping(value = "listar", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<City>> getAllCities(@RequestParam(value = "page") Integer page,
                                                   @RequestParam(value = "elementsPerPage") Integer elementsPerPage) {
        log.info("Requisição Rest: listar recebida");
        return ResponseEntity.ok(cityService.getAll(page, elementsPerPage));
    }
}
