package ar.edu.utn.frc.tup.lciii.spring_boot_api_example.repositories.jpa;

import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.entities.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchJpaRepository extends JpaRepository<MatchEntity, Long> {
}
