package br.com.planilha.leitorapp.infrastructure.persistence;

import br.com.planilha.leitorapp.domain.city.CityRequest;
import br.com.planilha.leitorapp.domain.city.CityResponse;
import br.com.planilha.leitorapp.domain.client.distrito.DistritoResponse;
import br.com.planilha.leitorapp.domain.provider.PersistenceProvider;
import br.com.planilha.leitorapp.domain.region.RegionRequest;
import br.com.planilha.leitorapp.domain.region.RegionResponse;
import br.com.planilha.leitorapp.domain.state.StateRequest;
import br.com.planilha.leitorapp.domain.state.StateResponse;
import br.com.planilha.leitorapp.domain.client.estado.EstadoResponse;
import br.com.planilha.leitorapp.infrastructure.persistence.city.CityEntity;
import br.com.planilha.leitorapp.infrastructure.persistence.city.CityRepository;
import br.com.planilha.leitorapp.infrastructure.persistence.district.DistrictRepository;
import br.com.planilha.leitorapp.infrastructure.persistence.microregion.MicroregionRepository;
import br.com.planilha.leitorapp.infrastructure.persistence.region.RegionEntity;
import br.com.planilha.leitorapp.infrastructure.persistence.region.RegionRepository;
import br.com.planilha.leitorapp.infrastructure.persistence.state.StateEntity;
import br.com.planilha.leitorapp.infrastructure.persistence.state.StateRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
@AllArgsConstructor
public class DatabaseProvider implements PersistenceProvider {

    private final RegionRepository regionRepository;
    private final StateRepository stateRepository;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final MicroregionRepository microregionRepository;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void saveAllRegions(List<EstadoResponse> estadoResponses) {
        regionRepository.saveAllAndFlush(estadoResponses.stream().map(EstadoResponse::toRegionEntity).toList());
    }

    @Override
    public RegionResponse upsertRegion(Long id, RegionRequest regionRequest) {
        RegionEntity regionEntity = regionRepository.saveAndFlush(regionRequest.toEntity(id));
        return regionEntity.toModel();
    }

    @Override
    public List<RegionResponse> getAllRegions() {
        return regionRepository.findAllSortedByName().stream().map(RegionEntity::toModel).toList();
    }

    @Override
    public boolean existsRegionById(Long id) {
        return regionRepository.existsById(id);
    }

    @Override
    public void deleteRegionById(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public void saveAllStates(List<EstadoResponse> estadoResponses) {
        stateRepository.saveAllAndFlush(estadoResponses.stream().map(EstadoResponse::toStateEntity).toList());
    }

    @Override
    public StateResponse upsertState(Long id, StateRequest stateRequest) {
        StateEntity stateEntity = stateRepository.saveAndFlush(stateRequest.toEntity(id));
        return stateEntity.toModel();
    }

    @Override
    public List<StateResponse> getAllStates() {
        return stateRepository.findAllSortedByAcronym().stream().map(StateEntity::toModel).toList();
    }

    @Override
    public boolean existsStateById(Long id) {
        return stateRepository.existsById(id);
    }

    @Override
    public void deleteStateById(Long id) {
        stateRepository.deleteById(id);
    }

    @Override
    public CityResponse upsertCity(Long id, CityRequest cityRequest) {
        CityEntity cityEntity = cityRepository.saveAndFlush(cityRequest.toEntity(id));
        return cityEntity.toModel();
    }

    @Override
    public void saveAllCities(List<CityResponse> cities) {
        String sql = "INSERT INTO cidades (id_cidade_planilha, nome, nome_abreviado, latitude, longitude) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, cities.get(i).getIdCityWorksheet());
                ps.setString(2, cities.get(i).getName());
                ps.setString(3, cities.get(i).getShortName());
                ps.setString(4, cities.get(i).getLatitude());
                ps.setString(5, cities.get(i).getLongitude());
            }

            @Override
            public int getBatchSize() {
                return cities.size();
            }
        });
    }

    @Override
    public List<CityResponse> getAllCities(Pageable pageable) {
        return cityRepository.findAllByName(pageable).stream().map(CityEntity::toModel).toList();
    }

    @Override
    public boolean existsCityById(Long id) {
        return cityRepository.existsById(id);
    }

    @Override
    public void deleteCityById(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public void saveAllDistrict(List<DistritoResponse> distritosResponse) {
        districtRepository.saveAllAndFlush(distritosResponse.stream().map(DistritoResponse::toDistrictEntity).toList());
    }

    @Override
    public void saveAllMicroregions(List<DistritoResponse> distritosResponse) {
        microregionRepository.saveAllAndFlush(distritosResponse.stream().map(DistritoResponse::toMicroregionEntity).toList());
    }
}
