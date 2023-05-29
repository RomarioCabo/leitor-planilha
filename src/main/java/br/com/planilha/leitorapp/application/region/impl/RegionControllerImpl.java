package br.com.planilha.leitorapp.application.region.impl;

import br.com.planilha.leitorapp.application.region.RegionController;
import br.com.planilha.leitorapp.application.uri.Uri;
import br.com.planilha.leitorapp.domain.region.RegionRequest;
import br.com.planilha.leitorapp.domain.region.RegionResponse;
import br.com.planilha.leitorapp.domain.region.RegionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("api/v1/regioes/")
@AllArgsConstructor
public class RegionControllerImpl extends Uri implements RegionController {

    private final RegionService regionService;

    @Override
    @PostMapping(value = "salvar-varias")
    public ResponseEntity<Void> saveAll() {
        log.info("Requisição Rest: salvar várias recebida");
        regionService.saveAll();
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping(value = "salvar", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RegionResponse> save(@Valid @RequestBody RegionRequest regionRequest) {
        log.info("Requisição Rest: salvar recebida");
        RegionResponse regionResponse = regionService.upsert(null, regionRequest);
        return ResponseEntity.created(generate("/api/v1/regioes/listar", null, null)).body(regionResponse);
    }

    @Override
    @PutMapping(value = "alterar/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RegionResponse> update(@PathVariable Long id, @Valid @RequestBody RegionRequest regionRequest) {
        log.info("Requisição Rest: alterar/{}", id);
        RegionResponse regionResponse = regionService.upsert(id, regionRequest);
        return ResponseEntity.ok(regionResponse);
    }

    @Override
    @DeleteMapping(value = "deletar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Requisição Rest: deletar/{}", id);
        regionService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping(value = "listar", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RegionResponse>> getAll() {
        log.info("Requisição Rest: listar recebida");
        return ResponseEntity.ok(regionService.getAll());
    }
}
