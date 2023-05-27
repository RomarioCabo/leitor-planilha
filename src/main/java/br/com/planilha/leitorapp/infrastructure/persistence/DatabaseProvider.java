package br.com.planilha.leitorapp.infrastructure.persistence;

import br.com.planilha.leitorapp.domain.city.City;
import br.com.planilha.leitorapp.domain.provider.PersistenceProvider;
import br.com.planilha.leitorapp.infrastructure.persistence.city.CityEntity;
import br.com.planilha.leitorapp.infrastructure.persistence.city.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DatabaseProvider implements PersistenceProvider {

    private final CityRepository cityRepository;

    @Override
    public List<City> saveAllCities(List<City> cities) {
        List<CityEntity> citiesEntity = cityRepository.saveAll(cities.stream().map(City::toEntity).toList());
        return citiesEntity.stream().map(CityEntity::toCity).toList();
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAllByName().stream().map(CityEntity::toCity).toList();
    }
}
