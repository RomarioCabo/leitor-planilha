package br.com.planilha.leitorapp.domain.city.impl;

import br.com.planilha.leitorapp.domain.city.CityRequest;
import br.com.planilha.leitorapp.domain.city.CityResponse;
import br.com.planilha.leitorapp.domain.city.CityService;
import br.com.planilha.leitorapp.domain.city.exception.CityException;
import br.com.planilha.leitorapp.domain.city.exception.CityNotFoundException;
import br.com.planilha.leitorapp.domain.provider.PersistenceProvider;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private final PersistenceProvider provider;

    @Override
    public CityResponse upsert(Long id, CityRequest cityRequest) {
        existsId(id);

        try {
            return provider.saveCity(id, cityRequest);
        } catch (Exception e) {
            throw new CityException();
        }
    }

    @Override
    public List<CityResponse> saveAll(List<CityResponse> cities) {
        try {
            return provider.saveAllCities(cities);
        } catch (Exception e) {
            throw new CityException();
        }
    }

    @Override
    public void delete(Long id) {
        existsId(id);

        try {
            provider.delete(id);
        } catch (Exception e) {
            throw new CityException();
        }
    }

    @Override
    @Cacheable(value = "cities", key = "{#page, #elementsPerPage}", cacheManager = "getCitiesCacheManager")
    public List<CityResponse> getAll(Integer page, Integer elementsPerPage) {
        PageRequest pageRequest = PageRequest.of(page, elementsPerPage, ASC, "name");
        return provider.getAllCities(pageRequest);
    }

    private void existsId(Long id) {
        boolean exists;

        try {
            exists = provider.existsCityById(id);
        } catch (Exception e) {
            throw new CityException();
        }

        if (id != null && !exists) {
            throw new CityNotFoundException(id);
        }
    }
}
