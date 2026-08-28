package edu.co.sena.worksite.respositories;

import edu.co.sena.worksite.entities.OfertaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfertaRepository extends
        JpaRepository<OfertaEntity, Long>,
        JpaSpecificationExecutor<OfertaEntity> {

    List<OfertaEntity> findByEmpresa_Id(int idEmpresa);

    long countByEstado(String estado);
}
