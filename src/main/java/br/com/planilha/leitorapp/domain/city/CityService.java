package br.com.planilha.leitorapp.domain.city;

import java.util.List;

public interface CityService {

    List<City> saveAll(List<City> cities);

    List<City> getAll(Integer page, Integer elementsPerPage);
}
