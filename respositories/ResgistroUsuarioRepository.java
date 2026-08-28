package edu.co.sena.worksite.respositories;

import edu.co.sena.worksite.entities.ResgistroUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResgistroUsuarioRepository extends
        JpaRepository<ResgistroUsuarioEntity, Long>,
        JpaSpecificationExecutor<ResgistroUsuarioEntity> {

    Optional<ResgistroUsuarioEntity> findById(long id);

    Optional<ResgistroUsuarioEntity> findByCorreoElectronico(String correoElectronico);

    long countByRolNombre_RolNombre(String rolNombre);


}
