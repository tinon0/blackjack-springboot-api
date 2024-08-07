package ar.edu.utn.frc.tup.lciii.spring_boot_api_example.services.impl;

import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.dtos.common.MatchDto;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.dtos.common.RoundDto;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.entities.CartaEntity;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.entities.MatchEntity;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.entities.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.entities.RoundEntity;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.models.Carta;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.models.Match;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.models.Round;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.repositories.jpa.MatchJpaRepository;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.services.CartaService;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.services.MatchService;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.services.PlayerService;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.services.RoundService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

@Service

public class MatchServiceImpl implements MatchService {
    @Autowired
    private MatchJpaRepository matchJpaRepository;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CartaService cartaService;
    @Autowired
    private RoundService roundService;

    @Override
    public Match crearMatch() {

        MatchEntity matchEntity = new MatchEntity(); //creo el MatchE

        PlayerEntity playerEntity = playerService.buscarPlayers(1000000L); //busco el PLayerE
        matchEntity.setPlayer(playerEntity); //se lo agregao al MatchE

        RoundEntity roundEntity = new RoundEntity(); //creo la RoundE
        Round round = crearRound(); //creo un Round

        List<CartaEntity> cartaEntitiesApp = new ArrayList<>(); //creo una lista de CartasE
        round.getCartasApp().forEach(
                c -> cartaEntitiesApp.add(modelMapper.map(c, CartaEntity.class)) //a cada Carta de la Round, lo mape en la lista
        );
        roundEntity.setCartasApp(cartaEntitiesApp); //y se lo agrego a la RoundE
        cartaService.saveCartas(cartaEntitiesApp); //guardo las cartas en la DB

        List<CartaEntity> cartaEntitiesPlayer = new ArrayList<>(); //mismo procedimiento
        round.getCartasPlayer().forEach(
                c -> cartaEntitiesPlayer.add(modelMapper.map(c, CartaEntity.class))
        );
        roundEntity.setCartasPlayer(cartaEntitiesPlayer);
        cartaService.saveCartas(cartaEntitiesPlayer); //guardo las cartas en la DB

        List<CartaEntity> mazoEntity = new ArrayList<>();
        round.getMazo().forEach(
                carta -> mazoEntity.add(modelMapper.map(carta, CartaEntity.class))
        );
        roundEntity.setMazo(mazoEntity); // aqui se guarda el mazo usado en el RoundE
        cartaService.saveCartas(mazoEntity); // Guardo las cartas del mazo en la DB

        roundEntity.setFinished(round.isFinished()); //pongo que la ronda no temrino

        RoundEntity roundEntitySaved = roundService.guardarRound(roundEntity); //guardamos la ronda en la DB
        matchEntity.setRound(roundEntitySaved); //metemos la ronda guardada en el MatchE

        MatchEntity matchEntitySaved = matchJpaRepository.save(matchEntity); //guardamos la match en la DB

        return modelMapper.map(matchEntitySaved, Match.class); //devolvemos esa MatchE guardada en una Match
    }

    @Override
    public Round crearRound() {
        List<Carta> cartasParaPlayer = new ArrayList<>(); //la mano del player
        List<Carta> cartasParaApp = new ArrayList<>();    //la mano de la app

        Round round = new Round();
        round.setMazo(cartaService.crearMazo());
        round.setWinner("");

        //Damos dos cartas al Player
        cartasParaPlayer.add(round.getMazo().pop());
        cartasParaPlayer.add(round.getMazo().pop());

        round.setCartasPlayer(cartasParaPlayer);

        //Damos una carta a la App
        cartasParaApp.add(round.getMazo().pop());
        round.setCartasApp(cartasParaApp);

        round.setFinished(false);

        return round;
    }

    @Override
    public RoundDto mapearRound(Round round) {
        return roundService.mapearRound(round);
    }

    @Override
    public Match pedirCarta(Long idMatch, Long idRound) {
        Optional<MatchEntity> matchEntityOptional = matchJpaRepository.findById(idMatch); //busco la match con ese id
        MatchEntity matchEntity = matchEntityOptional.get();

        Optional<RoundEntity> roundEntityOptional = roundService.findById(idRound); //busco el round con ese id
        RoundEntity roundEntity = roundEntityOptional.get();

        Stack<CartaEntity> mazo = new Stack<>();
        mazo.addAll(roundEntity.getMazo()); //relleno un mazo con el mazo anterior

        CartaEntity nuevaCarta = mazo.pop();
        if (!roundEntity.getCartasPlayer().contains(nuevaCarta)) {
            roundEntity.getCartasPlayer().add(nuevaCarta); //me fijo si ya lo tiene
        }

        List<CartaEntity> mazoList = new ArrayList<>(mazo); // guardar el stack a lista en roundEntity
        roundEntity.setMazo(mazoList);

        // ver si es mas grande que 21 o no
        if (cartaService.mayorA21(roundEntity)){
            roundEntity.setFinished(true);
        }

        RoundEntity roundEntitySaved = roundService.guardarRound(roundEntity); //guardamos la ronda
        matchEntity.setRound(roundEntitySaved); //guardamos la ronda salvada en la match
        MatchEntity matchEntitySaved = matchJpaRepository.save(matchEntity); //guardamos la match

        return modelMapper.map(matchEntitySaved, Match.class);
    }

    @Override
    public Match aguantarTurno(Long idMatch, Long idRound) {
        Optional<MatchEntity> matchEntityOptional = matchJpaRepository.findById(idMatch); //busco la match con ese id
        MatchEntity matchEntity = matchEntityOptional.get();

        Optional<RoundEntity> roundEntityOptional = roundService.findById(idRound); //busco el round con ese id
        RoundEntity roundEntity = roundEntityOptional.get();

        Stack<CartaEntity> mazo = new Stack<>();
        mazo.addAll(roundEntity.getMazo()); //relleno un mazo con el mazo anterior

        RoundEntity roundChequeada = cartaService.hasta18(roundEntity, mazo);
        //ver si hubo win
        RoundEntity roundChequeadaWin = roundService.huboWin(roundChequeada);

        RoundEntity roundEntitySaved = roundService.guardarRound(roundChequeadaWin);
        matchEntity.setRound(roundEntitySaved);
        MatchEntity matchEntitySaved = matchJpaRepository.save(matchEntity);

        return modelMapper.map(matchEntitySaved, Match.class);
    }

    @Override
    public MatchDto mapearMatchHold(Match match) { //todo uno para al pedir otro al holdear
        MatchDto matchDto = new MatchDto();
        matchDto.setIdPlayer(match.getPlayer().getId());

        if (match.getRound().isFinished() && match.getRound().getWinner().equals("PLAYER")) {
            matchDto.setMensaje("PARTIDA TERMINADA, PLAYER GANA");
        } else if (match.getRound().isFinished() && match.getRound().getWinner().equals("APP")) {
            matchDto.setMensaje("PARTIDA TERMINADA, APP GANA");
        } else if (match.getRound().isFinished() && match.getRound().getWinner().equals("EMPATE")) {
            matchDto.setMensaje("PARTIDA EMPATADA");
        } else {
            matchDto.setMensaje("PARTIDA EN JUEGO");
        }

        matchDto.setRound(mapearRound(match.getRound()));
        return matchDto;
    }
    @Override
    public MatchDto mapearMatchPedir(Match match){
        MatchDto matchDto = new MatchDto();
        matchDto.setIdPlayer(match.getPlayer().getId());
        if (match.getRound().isFinished()){ //esta comprobacion siempre es del lado del player porque app no se va a pasar
            matchDto.setMensaje("PARTIDA YA PERDIDA");
        }else{
            matchDto.setMensaje("PARTIDA EN JUEGO");
        }
        matchDto.setRound(mapearRound(match.getRound()));
        return matchDto;
    }

}
