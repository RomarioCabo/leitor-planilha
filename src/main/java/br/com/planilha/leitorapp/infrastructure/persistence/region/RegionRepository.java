package br.com.planilha.leitorapp.infrastructure.persistence.region;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Long> {

    @Transactional(readOnly = true)
    @Query(value = "SELECT region FROM RegionEntity region ORDER BY region.name")
    List<RegionEntity> findAllSortedByName();
}
