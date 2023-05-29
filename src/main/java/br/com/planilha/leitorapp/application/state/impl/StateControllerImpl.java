package br.com.planilha.leitorapp.application.state.impl;

import br.com.planilha.leitorapp.application.state.StateController;
import br.com.planilha.leitorapp.application.uri.Uri;
import br.com.planilha.leitorapp.domain.state.StateRequest;
import br.com.planilha.leitorapp.domain.state.StateResponse;
import br.com.planilha.leitorapp.domain.state.StateService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("api/v1/estados/")
@AllArgsConstructor
public class StateControllerImpl extends Uri implements StateController {

    private final StateService stateService;

    @Override
    @PostMapping(value = "salvar-varios")
    public ResponseEntity<Void> saveAll() {
        log.info("Requisição Rest: salvar várias recebida");
        stateService.saveAll();
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping(value = "salvar", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<StateResponse> save(@Valid @RequestBody StateRequest regionRequest) {
        log.info("Requisição Rest: salvar recebida");
        StateResponse regionResponse = stateService.upsert(null, regionRequest);
        return ResponseEntity.created(generate("/api/v1/estados/listar", null, null)).body(regionResponse);
    }

    @Override
    @PutMapping(value = "alterar/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<StateResponse> update(@PathVariable Long id, @Valid @RequestBody StateRequest regionRequest) {
        log.info("Requisição Rest: alterar/{}", id);
        StateResponse regionResponse = stateService.upsert(id, regionRequest);
        return ResponseEntity.ok(regionResponse);
    }

    @Override
    @DeleteMapping(value = "deletar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Requisição Rest: deletar/{}", id);
        stateService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping(value = "listar", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StateResponse>> getAll() {
        log.info("Requisição Rest: listar recebida");
        return ResponseEntity.ok(stateService.getAll());
    }
}
