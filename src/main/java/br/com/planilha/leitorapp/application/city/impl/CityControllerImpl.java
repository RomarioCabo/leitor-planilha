package br.com.planilha.leitorapp.application.city.impl;

import br.com.planilha.leitorapp.application.city.CityController;
import br.com.planilha.leitorapp.domain.city.City;
import br.com.planilha.leitorapp.domain.city.CityService;
import br.com.planilha.leitorapp.domain.spreadsheet.SpreadsheetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Slf4j
@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class CityControllerImpl implements CityController {

    private final SpreadsheetService spreadsheetService;
    private final CityService cityService;

    @PostMapping(value = "/upload-cidades", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<City>> saveCities(@RequestParam("spreadsheet") MultipartFile spreadsheet) {
        log.info("Requisição Rest recebida");
        List<City> cities = spreadsheetService.converter(spreadsheet);
        log.info("Inserindo cidades");
        cities = cityService.saveAll(cities);
        log.info("Cidades inseridas com sucesso!");
        return ResponseEntity.ok(cities);
    }
}
