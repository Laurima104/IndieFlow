package com.indieflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indieflow.model.Projeto;

@Repository 
public interface ProjetoRepository extends JpaRepository<Projeto, Long>{
    
}
