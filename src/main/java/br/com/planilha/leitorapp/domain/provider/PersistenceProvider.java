package br.com.planilha.leitorapp.domain.provider;

import br.com.planilha.leitorapp.domain.city.CityRequest;
import br.com.planilha.leitorapp.domain.city.CityResponse;
import br.com.planilha.leitorapp.domain.client.distrito.DistritoResponse;
import br.com.planilha.leitorapp.domain.region.RegionRequest;
import br.com.planilha.leitorapp.domain.region.RegionResponse;
import br.com.planilha.leitorapp.domain.state.StateRequest;
import br.com.planilha.leitorapp.domain.state.StateResponse;
import br.com.planilha.leitorapp.domain.client.estado.EstadoResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersistenceProvider {

    void saveAllRegions(List<EstadoResponse> estadoResponses);

    RegionResponse upsertRegion(Long id, RegionRequest regionRequest);

    List<RegionResponse> getAllRegions();

    boolean existsRegionById(Long id);

    void deleteRegionById(Long id);

    void saveAllStates(List<EstadoResponse> estadoResponses);

    StateResponse upsertState(Long id, StateRequest stateRequest);

    List<StateResponse> getAllStates();

    boolean existsStateById(Long id);

    void deleteStateById(Long id);

    CityResponse upsertCity(Long id, CityRequest cityRequest);

    void saveAllCities(List<CityResponse> cities);

    List<CityResponse> getAllCities(Pageable pageable);

    boolean existsCityById(Long id);

    void deleteCityById(Long id);

    void saveAllDistrict(List<DistritoResponse> distritosResponse);
}
