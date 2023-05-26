package br.com.planilha.leitorapp.application.city.impl;

import br.com.planilha.leitorapp.application.city.CityController;
import br.com.planilha.leitorapp.domain.spreadsheet.SpreadsheetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Slf4j
@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class CityControllerImpl implements CityController {

    private final SpreadsheetService spreadsheetService;

    @PostMapping(value = "/upload-cidades", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveCities(@RequestParam("spreadsheet") MultipartFile spreadsheet) {
        log.info("Requisição Rest recebida");
        spreadsheetService.converter(spreadsheet);
        return ResponseEntity.ok().build();
    }
}
