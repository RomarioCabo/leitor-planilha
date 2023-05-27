package br.com.planilha.leitorapp.infrastructure.persistence;

import br.com.planilha.leitorapp.domain.city.CityRequest;
import br.com.planilha.leitorapp.domain.city.CityResponse;
import br.com.planilha.leitorapp.domain.provider.PersistenceProvider;
import br.com.planilha.leitorapp.infrastructure.persistence.city.CityEntity;
import br.com.planilha.leitorapp.infrastructure.persistence.city.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DatabaseProvider implements PersistenceProvider {

    private final CityRepository cityRepository;

    @Override
    public CityResponse saveCity(Long id, CityRequest cityRequest) {
        CityEntity cityEntity = cityRepository.saveAndFlush(cityRequest.toEntity(id));
        return cityEntity.toCity();
    }

    @Override
    public List<CityResponse> saveAllCities(List<CityResponse> cities) {
        List<CityEntity> citiesEntity = cityRepository.saveAllAndFlush(cities.stream().map(CityResponse::toEntity).toList());
        return citiesEntity.stream().map(CityEntity::toCity).toList();
    }

    @Override
    public List<CityResponse> getAllCities(Pageable pageable) {
        return cityRepository.findAllByName(pageable).stream().map(CityEntity::toCity).toList();
    }

    @Override
    public boolean existsCityById(Long id) {
        return cityRepository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        cityRepository.deleteById(id);
    }
}
