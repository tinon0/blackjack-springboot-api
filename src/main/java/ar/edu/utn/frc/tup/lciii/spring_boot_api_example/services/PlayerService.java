package ar.edu.utn.frc.tup.lciii.spring_boot_api_example.services;

import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.entities.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.models.*;
import org.springframework.stereotype.Service;

@Service
public interface PlayerService {
    PlayerEntity buscarPlayers(Long id);
}
