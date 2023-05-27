package br.com.planilha.leitorapp.infrastructure.persistence.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {

    @Query(value = "select city from CityEntity city order by city.name")
    List<CityEntity> findAllByName();
}
