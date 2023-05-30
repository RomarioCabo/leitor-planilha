package br.com.planilha.leitorapp.domain.provider;

import br.com.planilha.leitorapp.domain.client.distrito.DistritoResponse;
import br.com.planilha.leitorapp.domain.client.estado.EstadoResponse;

import java.util.List;

public interface FeignProvider {

    List<EstadoResponse> getEstados();

    List<DistritoResponse> getDistritos();
}
