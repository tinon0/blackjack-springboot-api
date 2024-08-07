package ar.edu.utn.frc.tup.lciii.spring_boot_api_example.repositories.jpa;

import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerJpaRepository extends JpaRepository<PlayerEntity, Long> {
    Optional<PlayerEntity> findAllById(Long id);
}
