package ar.edu.utn.frc.tup.lciii.spring_boot_api_example.services.impl;

import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.dtos.common.RoundDto;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.entities.CartaEntity;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.entities.RoundEntity;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.models.Round;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.repositories.jpa.RoundJpaRepository;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.services.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RoundServiceImpl implements RoundService {
    @Autowired
    private RoundJpaRepository roundJpaRepository;

    @Override
    public RoundEntity guardarRound(RoundEntity roundEntityToSave) {
        return roundJpaRepository.save(roundEntityToSave);
    }

    @Override
    public Optional<RoundEntity> findById(Long idRound) {
        return roundJpaRepository.findById(idRound);
    }

    @Override
    public RoundDto mapearRound(Round round) {
        RoundDto roundDto = new RoundDto();
        roundDto.setCartasApp(round.getCartasApp());
        roundDto.setCartasPlayer(round.getCartasPlayer());

        AtomicInteger sumaApp = new AtomicInteger();
        round.getCartasApp().forEach(
                carta -> {
                    sumaApp.set(sumaApp.get() + carta.getNumero());
                }
        );
        roundDto.setSumaApp(sumaApp.intValue());

        AtomicInteger sumaPlayer = new AtomicInteger();
        round.getCartasPlayer().forEach(
                carta -> {
                    sumaPlayer.set(sumaPlayer.get() + carta.getNumero());
                }
        );
        roundDto.setSumaPlayer(sumaPlayer.intValue());
        return roundDto;
    }

    @Override
    public RoundEntity huboWin(RoundEntity roundEntity) {
        int sumaApp = sumarCartas(roundEntity.getCartasApp());
        int sumaPlayer = sumarCartas(roundEntity.getCartasPlayer());

        if (Math.abs(sumaPlayer - 21) < Math.abs(sumaApp - 21)) { //usamos el valor absoluto por si
                                                                  // suma < 21, ej: (12 - 21) = -9
            roundEntity.setWinner("PLAYER");
        } else if (Math.abs(sumaPlayer - 21) > Math.abs(sumaApp - 21)) {
            roundEntity.setWinner("APP");
        } else {
            roundEntity.setWinner("EMPATE"); //no me acuerdo si habia empate en esto
        }

        roundEntity.setFinished(true); //termino
        return roundEntity;
    }

    private int sumarCartas(List<CartaEntity> cartas){
        AtomicInteger suma = new AtomicInteger();
        cartas.forEach(
                cartaEntity -> suma.set(suma.get() + cartaEntity.getNumero())
        );
        return suma.intValue();
    }
}
