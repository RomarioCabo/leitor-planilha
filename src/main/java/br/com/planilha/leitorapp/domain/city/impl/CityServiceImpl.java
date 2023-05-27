package br.com.planilha.leitorapp.domain.city.impl;

import br.com.planilha.leitorapp.domain.city.City;
import br.com.planilha.leitorapp.domain.city.CityService;
import br.com.planilha.leitorapp.domain.city.exception.CityException;
import br.com.planilha.leitorapp.domain.provider.PersistenceProvider;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private final PersistenceProvider provider;

    @Override
    public List<City> saveAll(List<City> cities) {
        try {
            return provider.saveAllCities(cities);
        } catch (Exception e) {
            throw new CityException();
        }
    }

    @Override
    public List<City> getAll(Integer page, Integer elementsPerPage) {
        PageRequest pageRequest = PageRequest.of(page, elementsPerPage, ASC, "name");
        return provider.getAllCities(pageRequest);
    }
}
