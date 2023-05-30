package br.com.planilha.leitorapp.domain.region.impl;

import br.com.planilha.leitorapp.domain.log.LogMessage;
import br.com.planilha.leitorapp.domain.provider.FeignProvider;
import br.com.planilha.leitorapp.domain.provider.PersistenceProvider;
import br.com.planilha.leitorapp.domain.region.RegionRequest;
import br.com.planilha.leitorapp.domain.region.RegionResponse;
import br.com.planilha.leitorapp.domain.region.RegionService;
import br.com.planilha.leitorapp.domain.region.exception.RegionException;
import br.com.planilha.leitorapp.domain.region.exception.RegionNotFoundException;
import br.com.planilha.leitorapp.domain.client.estado.EstadoResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class RegionServiceImpl extends LogMessage implements RegionService {

    private final PersistenceProvider persistenceProvider;
    private final FeignProvider feignProvider;

    @Override
    public void saveAll() {
        try {
            List<EstadoResponse> estadoResponses = feignProvider.getEstados();
            persistenceProvider.saveAllRegions(estadoResponses);
        } catch (Exception e) {
            logMessageError(e);
            throw new RegionException();
        }
    }

    @Override
    public RegionResponse upsert(Long id, RegionRequest regionRequest) {
        existsId(id);

        try {
            return persistenceProvider.upsertRegion(id, regionRequest);
        } catch (Exception e) {
            logMessageError(e);
            throw new RegionException();
        }
    }

    @Override
    public void delete(Long id) {
        existsId(id);

        try {
            persistenceProvider.deleteRegionById(id);
        } catch (Exception e) {
            logMessageError(e);
            throw new RegionException();
        }
    }

    @Override
    @Cacheable(value = "regions", cacheManager = "getRegionsCacheManager")
    public List<RegionResponse> getAll() {
        try {
            return persistenceProvider.getAllRegions();
        } catch (Exception e) {
            logMessageError(e);
            throw new RegionException();
        }
    }

    private void existsId(Long id) {
        boolean exists;

        try {
            exists = persistenceProvider.existsRegionById(id);
        } catch (Exception e) {
            logMessageError(e);
            throw new RegionException();
        }

        if (id != null && !exists) {
            throw new RegionNotFoundException(id);
        }
    }
}
