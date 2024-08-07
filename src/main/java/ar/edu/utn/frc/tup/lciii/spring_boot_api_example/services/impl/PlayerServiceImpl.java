package ar.edu.utn.frc.tup.lciii.spring_boot_api_example.services.impl;

import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.entities.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.models.Player;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.repositories.jpa.PlayerJpaRepository;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.services.PlayerService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerJpaRepository playerJpaRepository;
//    @Autowired
//    private ModelMapper modelMapper;

    @Override
    public PlayerEntity buscarPlayers(Long id) {
        Optional<PlayerEntity> playerEntityOptional = playerJpaRepository.findAllById(id);
        if (playerEntityOptional.isEmpty()){
            throw new EntityNotFoundException();
        }
        else {
            return playerEntityOptional.get();
        }
    }
}
