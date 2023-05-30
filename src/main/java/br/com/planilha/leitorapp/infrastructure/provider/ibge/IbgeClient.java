package br.com.planilha.leitorapp.infrastructure.provider.ibge;

import br.com.planilha.leitorapp.domain.client.distrito.DistritoResponse;
import br.com.planilha.leitorapp.domain.client.estado.EstadoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "ibge-http-client", url = "${ibge.url}")
public interface IbgeClient {

    @GetMapping(path = "/api/v1/localidades/estados", produces = APPLICATION_JSON_VALUE)
    List<EstadoResponse> getEstados();

    @GetMapping(path = "/api/v1/localidades/distritos", produces = APPLICATION_JSON_VALUE)
    List<DistritoResponse> getDistritos();
}
