package br.com.planilha.leitorapp.domain.state;

import java.util.List;

public interface StateService {

    void saveAll();

    StateResponse upsert(Long id, StateRequest stateRequest);

    void delete(Long id);

    List<StateResponse> getAll();
}
