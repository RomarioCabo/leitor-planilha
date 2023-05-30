package br.com.planilha.leitorapp.infrastructure.persistence.district;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {

    @Transactional(readOnly = true)
    @Query(value = "SELECT district FROM DistrictEntity district ORDER BY district.name")
    List<DistrictEntity> findAllSortedByName();
}
