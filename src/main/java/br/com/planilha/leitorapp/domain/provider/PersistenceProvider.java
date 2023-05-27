package br.com.planilha.leitorapp.domain.provider;

import br.com.planilha.leitorapp.domain.city.CityRequest;
import br.com.planilha.leitorapp.domain.city.CityResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersistenceProvider {

    CityResponse saveCity(Long id, CityRequest cityRequest);

    void saveAllCities(List<CityResponse> cities);

    List<CityResponse> getAllCities(Pageable pageable);

    boolean existsCityById(Long id);

    void delete(Long id);
}
