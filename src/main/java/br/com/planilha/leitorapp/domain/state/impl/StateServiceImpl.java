package br.com.planilha.leitorapp.domain.state.impl;

import br.com.planilha.leitorapp.domain.log.LogMessage;
import br.com.planilha.leitorapp.domain.provider.FeignProvider;
import br.com.planilha.leitorapp.domain.provider.PersistenceProvider;
import br.com.planilha.leitorapp.domain.state.StateRequest;
import br.com.planilha.leitorapp.domain.state.StateResponse;
import br.com.planilha.leitorapp.domain.state.StateService;
import br.com.planilha.leitorapp.domain.state.exception.StateException;
import br.com.planilha.leitorapp.domain.state.exception.StateNotFoundException;
import br.com.planilha.leitorapp.domain.state.json.EstadoResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class StateServiceImpl extends LogMessage implements StateService {

    private final PersistenceProvider provider;
    private final FeignProvider feignProvider;

    @Override
    public void saveAll() {
        try {
            List<EstadoResponse> estadoResponses = feignProvider.getEstados();
            provider.saveAllStates(estadoResponses);
        } catch (Exception e) {
            logMessageError(e);
            throw new StateException();
        }
    }

    @Override
    public StateResponse upsert(Long id, StateRequest stateRequest) {
        existsId(id);

        try {
            return provider.upsertState(id, stateRequest);
        } catch (Exception e) {
            logMessageError(e);
            throw new StateException();
        }
    }

    @Override
    public void delete(Long id) {
        existsId(id);

        try {
            provider.deleteStateById(id);
        } catch (Exception e) {
            logMessageError(e);
            throw new StateException();
        }
    }

    @Override
    @Cacheable(value = "states", cacheManager = "getStatesCacheManager")
    public List<StateResponse> getAll() {
        try {
            return provider.getAllStates();
        } catch (Exception e) {
            logMessageError(e);
            throw new StateException();
        }
    }

    private void existsId(Long id) {
        boolean exists;

        try {
            exists = provider.existsStateById(id);
        } catch (Exception e) {
            logMessageError(e);
            throw new StateException();
        }

        if (id != null && !exists) {
            throw new StateNotFoundException(id);
        }
    }
}
