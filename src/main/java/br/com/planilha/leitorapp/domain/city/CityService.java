package br.com.planilha.leitorapp.domain.city;

import java.util.List;

public interface CityService {

    CityResponse upsert(Long id, CityRequest cityRequest);

    void saveAll(List<CityResponse> cities);

    void delete(Long id);

    List<CityResponse> getAll(Integer page, Integer elementsPerPage);
}
