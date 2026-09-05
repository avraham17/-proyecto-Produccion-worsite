package edu.co.sena.worksite.respositories;

import edu.co.sena.worksite.dtos.EmpresaResumenDto;
import edu.co.sena.worksite.entities.EmpresaEntity;
import edu.co.sena.worksite.entities.OfertaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends
        JpaRepository<EmpresaEntity, Long>,
        JpaSpecificationExecutor<EmpresaEntity> {

    Optional<EmpresaEntity> findByCorreo(String correo);

    Optional<EmpresaEntity> findByIdUsuario(int idUsuario);

    @Query("SELECT new edu.co.sena.worksite.dtos.EmpresaResumenDto(" +
            "e.id, e.nombre, e.correo, COUNT(o.id)) " +
            "FROM EmpresaEntity e " +
            "LEFT JOIN OfertaEntity o ON o.empresa = e " +
            "GROUP BY e.id, e.nombre, e.correo")
    List<EmpresaResumenDto> listarEmpresas();
}
