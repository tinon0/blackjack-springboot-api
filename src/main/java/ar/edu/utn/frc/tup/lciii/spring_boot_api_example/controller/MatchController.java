package ar.edu.utn.frc.tup.lciii.spring_boot_api_example.controller;

import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.dtos.common.MatchDto;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.dtos.common.RoundDto;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.models.*;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.services.MatchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("blackjack/matches")
public class MatchController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MatchService matchService;

    @GetMapping("/crearMatch")
    public ResponseEntity<MatchDto> crearMatch(){
        Match match = matchService.crearMatch();
        return ResponseEntity.ok(matchService.mapearMatchPedir(match));
    }
    @PostMapping("/{idMatch}/round/{idRound}/play")
    public ResponseEntity<MatchDto> pedirCarta(@PathVariable Long idMatch,
                                               @PathVariable Long idRound){
        Match match = matchService.pedirCarta(idMatch, idRound);
        return ResponseEntity.ok(matchService.mapearMatchPedir(match));
    }
    @PostMapping("/{idMatch}/round/{idRound}/hold")
    public ResponseEntity<MatchDto> aguantarTurno(@PathVariable Long idMatch,
                                                  @PathVariable Long idRound){
        Match match = matchService.aguantarTurno(idMatch, idRound);
        return ResponseEntity.ok(matchService.mapearMatchHold(match));
    }
}
