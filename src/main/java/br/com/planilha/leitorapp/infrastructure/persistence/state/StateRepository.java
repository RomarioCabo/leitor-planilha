package br.com.planilha.leitorapp.infrastructure.persistence.state;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<StateEntity, Long> {

    @Transactional(readOnly = true)
    @Query(value = "SELECT state FROM StateEntity state ORDER BY state.acronym")
    List<StateEntity> findAllSortedByAcronym();
}
