package br.com.planilha.leitorapp.application.city.impl;

import br.com.planilha.leitorapp.application.city.CityController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Slf4j
@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class CityControllerImpl implements CityController {

    @PostMapping(value = "/salvar-cidades", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveCities() {
        return ResponseEntity.ok().build();
    }
}
