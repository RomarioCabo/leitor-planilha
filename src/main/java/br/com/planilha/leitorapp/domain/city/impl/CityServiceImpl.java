package br.com.planilha.leitorapp.domain.city.impl;

import br.com.planilha.leitorapp.domain.city.CityRequest;
import br.com.planilha.leitorapp.domain.city.CityResponse;
import br.com.planilha.leitorapp.domain.city.CityService;
import br.com.planilha.leitorapp.domain.city.exception.CityException;
import br.com.planilha.leitorapp.domain.city.exception.CityNotFoundException;
import br.com.planilha.leitorapp.domain.provider.PersistenceProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@Slf4j
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private static final String ERROR_MESSAGE = "Error: %s";

    private final PersistenceProvider provider;

    @Override
    public CityResponse upsert(Long id, CityRequest cityRequest) {
        existsId(id);

        try {
            return provider.saveCity(id, cityRequest);
        } catch (Exception e) {
            logMessageError(e);
            throw new CityException();
        }
    }

    @Override
    public void saveAll(List<CityResponse> cities) {
        try {
            provider.saveAllCities(cities);
        } catch (Exception e) {
            logMessageError(e);
            throw new CityException();
        }
    }

    @Override
    public void delete(Long id) {
        existsId(id);

        try {
            provider.delete(id);
        } catch (Exception e) {
            logMessageError(e);
            throw new CityException();
        }
    }

    @Override
    @Cacheable(value = "cities", key = "{#page, #elementsPerPage}", cacheManager = "getCitiesCacheManager")
    public List<CityResponse> getAll(Integer page, Integer elementsPerPage) {
        try {
            PageRequest pageRequest = PageRequest.of(page, elementsPerPage, ASC, "name");
            return provider.getAllCities(pageRequest);
        } catch (Exception e) {
            logMessageError(e);
            throw new CityException();
        }
    }

    private void existsId(Long id) {
        boolean exists;

        try {
            exists = provider.existsCityById(id);
        } catch (Exception e) {
            logMessageError(e);
            throw new CityException();
        }

        if (id != null && !exists) {
            throw new CityNotFoundException(id);
        }
    }

    private void logMessageError(Exception e) {
        log.info(String.format(ERROR_MESSAGE, e.getMessage()), e);
    }
}
