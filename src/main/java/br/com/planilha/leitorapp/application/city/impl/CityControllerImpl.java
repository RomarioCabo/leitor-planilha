package br.com.planilha.leitorapp.application.city.impl;

import br.com.planilha.leitorapp.application.city.CityController;
import br.com.planilha.leitorapp.application.uri.Uri;
import br.com.planilha.leitorapp.domain.city.CityRequest;
import br.com.planilha.leitorapp.domain.city.CityResponse;
import br.com.planilha.leitorapp.domain.city.CityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("api/v1/cidades/")
@AllArgsConstructor
public class CityControllerImpl extends Uri implements CityController {

    private final CityService cityService;

    @Override
    @PostMapping(value = "salvar", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CityResponse> save(@Valid @RequestBody CityRequest cityRequest) {
        log.info("Requisição Rest: salvar recebida");
        CityResponse cityResponse = cityService.upsert(null, cityRequest);
        return ResponseEntity.created(generate("/api/v1/cidades/listar", 0, 10)).body(cityResponse);
    }

    @Override
    @PutMapping(value = "alterar/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CityResponse> update(@PathVariable Long id, @Valid @RequestBody CityRequest cityRequest) {
        log.info("Requisição Rest: alterar/{}", id);
        CityResponse cityResponse = cityService.upsert(id, cityRequest);
        return ResponseEntity.ok(cityResponse);
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
}
