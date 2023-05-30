package br.com.planilha.leitorapp.application.district.impl;

import br.com.planilha.leitorapp.application.district.DistrictController;
import br.com.planilha.leitorapp.domain.district.DistrictService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/distritos/")
@AllArgsConstructor
public class DistrictControllerImpl implements DistrictController {

    private final DistrictService districtService;

    @Override
    @PostMapping(value = "salvar-varios")
    public ResponseEntity<Void> saveAll() {
        log.info("Requisição Rest: salvar vários recebida");
        districtService.saveAll();
        return ResponseEntity.ok().build();
    }
}
