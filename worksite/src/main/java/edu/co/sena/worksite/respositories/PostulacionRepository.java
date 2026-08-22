package edu.co.sena.worksite.respositories;

import edu.co.sena.worksite.entities.OfertaEntity;
import edu.co.sena.worksite.entities.PostulacionEntity;
import edu.co.sena.worksite.entities.ResgistroUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostulacionRepository extends
        JpaRepository<PostulacionEntity, Long>,
        JpaSpecificationExecutor<PostulacionEntity> {

    List<PostulacionEntity> findByOferta_Id(int idOferta);

    List<PostulacionEntity> findByCandidato_Id(int idCandidato);

    boolean existsByOfertaAndCandidato(OfertaEntity oferta, ResgistroUsuarioEntity candidato);

    long countByEstadoPostulacion(String estadoPostulacion);
}
