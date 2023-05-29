package br.com.planilha.leitorapp.domain.provider;

import br.com.planilha.leitorapp.domain.state.json.EstadoResponse;

import java.util.List;

public interface FeignProvider {

    List<EstadoResponse> getEstados();
}
