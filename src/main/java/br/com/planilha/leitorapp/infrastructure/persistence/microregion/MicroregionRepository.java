package br.com.planilha.leitorapp.infrastructure.persistence.microregion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MicroregionRepository extends JpaRepository<MicroregionEntity, Long> {

    @Transactional(readOnly = true)
    @Query(value = "SELECT mr FROM MicroregionEntity mr ORDER BY mr.name")
    List<MicroregionEntity> findAllSortedByName();
}
