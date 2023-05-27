package br.com.planilha.leitorapp.domain.provider;

import br.com.planilha.leitorapp.domain.city.City;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersistenceProvider {

    List<City> saveAllCities(List<City> cities);

    List<City> getAllCities(Pageable pageable);
}
