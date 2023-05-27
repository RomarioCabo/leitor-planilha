package br.com.planilha.leitorapp.infrastructure.persistence;

import br.com.planilha.leitorapp.domain.city.CityRequest;
import br.com.planilha.leitorapp.domain.city.CityResponse;
import br.com.planilha.leitorapp.domain.provider.PersistenceProvider;
import br.com.planilha.leitorapp.infrastructure.persistence.city.CityEntity;
import br.com.planilha.leitorapp.infrastructure.persistence.city.CityRepository;
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

    private final CityRepository cityRepository;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public CityResponse saveCity(Long id, CityRequest cityRequest) {
        CityEntity cityEntity = cityRepository.saveAndFlush(cityRequest.toEntity(id));
        return cityEntity.toCity();
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
