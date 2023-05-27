package br.com.planilha.leitorapp.application.city.impl;

import br.com.planilha.leitorapp.application.city.CityController;
import br.com.planilha.leitorapp.domain.city.CityRequest;
import br.com.planilha.leitorapp.domain.city.CityResponse;
import br.com.planilha.leitorapp.domain.city.CityService;
import br.com.planilha.leitorapp.domain.spreadsheet.SpreadsheetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
    @PostMapping(value = "salvar", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CityResponse> save(@RequestBody CityRequest cityRequest) {
        log.info("Requisição Rest: salvar recebida");
        CityResponse cityResponse = cityService.upsert(null, cityRequest);
        return ResponseEntity.created(getUri()).body(cityResponse);
    }

    @Override
    @PutMapping(value = "alterar/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CityResponse> update(@PathVariable Long id, @RequestBody CityRequest cityRequest) {
        log.info("Requisição Rest: alterar/{}", id);
        CityResponse cityResponse = cityService.upsert(id, cityRequest);
        return ResponseEntity.ok(cityResponse);
    }

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

    @Override
    @DeleteMapping(value = "deletar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Requisição Rest: deletar/{}", id);
        cityService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping(value = "listar", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CityResponse>> getAll(@RequestParam(value = "page") Integer page,
                                                     @RequestParam(value = "elementsPerPage") Integer elementsPerPage) {
        log.info("Requisição Rest: listar recebida");
        return ResponseEntity.ok(cityService.getAll(page, elementsPerPage));
    }

    private URI getUri() {
        return UriComponentsBuilder.fromPath("/api/v1/listar")
                .queryParam("page", "0")
                .queryParam("elementsPerPage", "10")
                .build()
                .toUri();
    }
}
