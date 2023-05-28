package br.com.planilha.leitorapp.domain.region.impl;

import br.com.planilha.leitorapp.domain.log.LogMessage;
import br.com.planilha.leitorapp.domain.provider.PersistenceProvider;
import br.com.planilha.leitorapp.domain.region.RegionRequest;
import br.com.planilha.leitorapp.domain.region.RegionResponse;
import br.com.planilha.leitorapp.domain.region.RegionService;
import br.com.planilha.leitorapp.domain.region.exception.RegionException;
import br.com.planilha.leitorapp.domain.region.exception.RegionNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class RegionServiceImpl extends LogMessage implements RegionService {

    private final PersistenceProvider provider;

    @Override
    public RegionResponse upsert(Long id, RegionRequest regionRequest) {
        existsId(id);

        try {
            return provider.upsertRegion(id, regionRequest);
        } catch (Exception e) {
            logMessageError(e);
            throw new RegionException();
        }
    }

    @Override
    public void delete(Long id) {
        existsId(id);

        try {
            provider.deleteRegionById(id);
        } catch (Exception e) {
            logMessageError(e);
            throw new RegionException();
        }
    }

    @Override
    @Cacheable(value = "regions", cacheManager = "getRegionsCacheManager")
    public List<RegionResponse> getAll() {
        try {
            return provider.getAllRegions();
        } catch (Exception e) {
            logMessageError(e);
            throw new RegionException();
        }
    }

    private void existsId(Long id) {
        boolean exists;

        try {
            exists = provider.existsRegionById(id);
        } catch (Exception e) {
            logMessageError(e);
            throw new RegionException();
        }

        if (id != null && !exists) {
            throw new RegionNotFoundException(id);
        }
    }
}
