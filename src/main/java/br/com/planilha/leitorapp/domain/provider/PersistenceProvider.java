package br.com.planilha.leitorapp.domain.provider;

import br.com.planilha.leitorapp.domain.city.City;

import java.util.List;

public interface PersistenceProvider {

    List<City> saveAllCities(List<City> cities);

    List<City> getAllCities();
}
