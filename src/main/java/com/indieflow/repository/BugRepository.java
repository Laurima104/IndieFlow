package com.indieflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.indieflow.model.Bug;

@Repository
public interface BugRepository extends JpaRepository<Bug, Long> {
    boolean existsByProjeto_Id(Long projetoId);
}
