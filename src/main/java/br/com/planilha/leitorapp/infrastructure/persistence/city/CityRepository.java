package br.com.planilha.leitorapp.infrastructure.persistence.city;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {

    @Transactional(readOnly = true)
    @Query(value = "select city from CityEntity city")
    Page<CityEntity> findAllByName(Pageable pageable);
}
