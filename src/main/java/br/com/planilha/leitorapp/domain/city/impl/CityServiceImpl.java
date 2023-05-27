package br.com.planilha.leitorapp.domain.city.impl;

import br.com.planilha.leitorapp.domain.city.City;
import br.com.planilha.leitorapp.domain.city.CityService;
import br.com.planilha.leitorapp.domain.city.exception.CityException;
import br.com.planilha.leitorapp.domain.provider.PersistenceProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
