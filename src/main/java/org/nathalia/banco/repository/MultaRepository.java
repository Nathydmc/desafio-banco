package org.nathalia.banco.repository;

import org.nathalia.banco.entity.MultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultaRepository extends JpaRepository<MultaEntity, Long>{

}
