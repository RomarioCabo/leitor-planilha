package br.com.planilha.leitorapp.application.spreadsheet.impl;

import br.com.planilha.leitorapp.application.spreadsheet.SpreadsheetController;
import br.com.planilha.leitorapp.domain.city.CityResponse;
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
@RequestMapping("api/v1/spreadsheet/")
@AllArgsConstructor
public class SpreadsheetControllerImpl implements SpreadsheetController {

    private final SpreadsheetService spreadsheetService;
    private final CityService cityService;

    @Override
    @PostMapping(value = "upload", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveAll(@RequestParam("spreadsheet") MultipartFile spreadsheet) {
        log.info("Requisição Rest: upload recebida");
        List<CityResponse> cities = spreadsheetService.converter(spreadsheet);
        log.info("Inserindo cidades");
        cityService.saveAll(cities);
        log.info("Cidades inseridas com sucesso!");
        return ResponseEntity.ok().build();
    }
}
