package ar.edu.utn.frc.tup.lciii.spring_boot_api_example.services;

import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.dtos.common.MatchDto;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.dtos.common.RoundDto;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.models.*;
import org.springframework.stereotype.Service;

@Service
public interface MatchService {
    Match crearMatch();
    Round crearRound();
    RoundDto mapearRound(Round round);
    Match pedirCarta(Long idMatch, Long idRound);
    Match aguantarTurno(Long idMatch, Long idRound);
    MatchDto mapearMatchHold(Match match);
    MatchDto mapearMatchPedir(Match match);
}
