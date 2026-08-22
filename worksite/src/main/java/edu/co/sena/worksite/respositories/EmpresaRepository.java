package edu.co.sena.worksite.respositories;

import edu.co.sena.worksite.entities.EmpresaEntity;
import edu.co.sena.worksite.entities.ResgistroUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends
        JpaRepository<EmpresaEntity, Long>,
        JpaSpecificationExecutor<EmpresaEntity> {

    Optional<EmpresaEntity> findByCorreo(String correo );

    Optional<EmpresaEntity> findByIdUsuario(int idUsuario);
}