package ar.edu.utn.frc.tup.lciii.spring_boot_api_example.repositories.jpa;

import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.entities.CartaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaJpaRepository extends JpaRepository<CartaEntity, Long> {
}
