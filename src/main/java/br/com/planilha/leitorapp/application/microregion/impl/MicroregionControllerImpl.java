package br.com.planilha.leitorapp.application.microregion.impl;

import br.com.planilha.leitorapp.application.microregion.MicroregionController;
import br.com.planilha.leitorapp.domain.microregion.MicroregionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/micro-regiao/")
@AllArgsConstructor
public class MicroregionControllerImpl implements MicroregionController {

    private final MicroregionService microregionService;

    @Override
    @PostMapping(value = "salvar-varios")
    public ResponseEntity<Void> saveAll() {
        log.info("Requisição Rest: salvar vários recebida");
        microregionService.saveAll();
        return ResponseEntity.ok().build();
    }
}
