package ar.edu.utn.frc.tup.lciii.spring_boot_api_example.services;

import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.dtos.common.RoundDto;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.entities.RoundEntity;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.models.Round;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RoundService {
    RoundEntity guardarRound(RoundEntity roundEntityToSave);
    Optional<RoundEntity> findById(Long idRound);
    RoundDto mapearRound(Round round);
    RoundEntity huboWin(RoundEntity roundEntity);
}
