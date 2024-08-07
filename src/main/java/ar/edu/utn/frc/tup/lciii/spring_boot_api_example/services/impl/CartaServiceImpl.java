package ar.edu.utn.frc.tup.lciii.spring_boot_api_example.services.impl;

import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.dtos.common.RoundDto;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.entities.CartaEntity;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.entities.RoundEntity;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.models.Carta;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.repositories.jpa.CartaJpaRepository;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.services.CartaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CartaServiceImpl implements CartaService {
    @Autowired
    private CartaJpaRepository cartaJpaRepository;
    @Override
    public Stack<Carta> crearMazo() {
        Stack<Carta> mazo = new Stack<>();
        //Creando los treboles
        Carta cartaTrebolA = new Carta("A", "TREBOL", 1);
        Carta cartaTrebolJ = new Carta("J", "TREBOL", 10);
        Carta cartaTrebolQ = new Carta("Q", "TREBOL", 10);
        Carta cartaTrebolK = new Carta("K", "TREBOL", 10);

        mazo.add(cartaTrebolA); mazo.add(cartaTrebolJ); mazo.add(cartaTrebolQ); mazo.add(cartaTrebolK);

        for (int i = 2; i < 11; i++) {
            Carta cartaTrebol = new Carta(Integer.toString(i), "TREBOL", i);
            mazo.add(cartaTrebol);
        }

        //Creando los diamantes
        Carta cartaDiamanteA = new Carta("A", "DIAMANTE", 1);
        Carta cartaDiamanteJ = new Carta("J", "DIAMANTE", 10);
        Carta cartaDiamanteQ = new Carta("Q", "DIAMANTE", 10);
        Carta cartaDiamanteK = new Carta("K", "DIAMANTE", 10);

        mazo.add(cartaDiamanteA); mazo.add(cartaDiamanteJ); mazo.add(cartaDiamanteQ); mazo.add(cartaDiamanteK);

        for (int i = 2; i < 11; i++) {
            Carta cartaDiamante = new Carta(Integer.toString(i), "DIAMANTE", i);
            mazo.add(cartaDiamante);
        }

        //Creando los corazones
        Carta cartaCorazonA = new Carta("A", "CORAZON", 1);
        Carta cartaCorazonJ = new Carta("J", "CORAZON", 10);
        Carta cartaCorazonQ = new Carta("Q", "CORAZON", 10);
        Carta cartaCorazonK = new Carta("K", "CORAZON", 10);

        mazo.add(cartaCorazonA); mazo.add(cartaCorazonJ); mazo.add(cartaCorazonQ); mazo.add(cartaCorazonK);

        for (int i = 2; i < 11; i++) {
            Carta cartaCorazon = new Carta(Integer.toString(i), "CORAZON", i);
            mazo.add(cartaCorazon);
        }
        //Creando los picas
        Carta cartaPicasA = new Carta("A", "PICAS", 1);
        Carta cartaPicasJ = new Carta("J", "PICAS", 10);
        Carta cartaPicasQ = new Carta("Q", "PICAS", 10);
        Carta cartaPicasK = new Carta("K", "PICAS", 10);

        mazo.add(cartaPicasA); mazo.add(cartaPicasJ); mazo.add(cartaPicasQ); mazo.add(cartaPicasK);

        for (int i = 2; i < 11; i++) {
            Carta cartaPicas = new Carta(Integer.toString(i), "PICAS", i);
            mazo.add(cartaPicas);
        }
        //Mezclando el mazo
        Collections.shuffle(mazo);
        return mazo;
    }
    @Override
    public void saveCartas(List<CartaEntity> cartas){
        cartaJpaRepository.saveAll(cartas);
    }

    @Override
    public boolean mayorA21(RoundEntity roundEntity) {
        AtomicInteger sumaPlayer = new AtomicInteger();
        roundEntity.getCartasPlayer().forEach(
                carta -> {
                    sumaPlayer.set(sumaPlayer.get() + carta.getNumero());
                }
        );
        return sumaPlayer.intValue() > 21;
    }

    @Override
    public RoundEntity hasta18(RoundEntity roundEntity, Stack<CartaEntity> mazo) {
        AtomicInteger sumaApp = new AtomicInteger();
        roundEntity.getCartasApp().forEach(
                carta -> {
                    sumaApp.set(sumaApp.get() + carta.getNumero());
                }
        );
        int sumaManoApp = sumaApp.intValue();
        int valorProximaCarta = mazo.peek().getNumero();
        int sumatoria = sumaManoApp + valorProximaCarta;

        while (sumaManoApp < 18) {
            int proximoValor = mazo.peek().getNumero();

            if (sumaManoApp + proximoValor > 18) { // vemos si sumar el valor de la prox carta al total se pasa de 18
                break;
            }
            CartaEntity proximaCarta = mazo.pop();

            roundEntity.getCartasApp().add(proximaCarta);

            sumaManoApp += proximaCarta.getNumero();
        }
        
        return roundEntity;
    }
}
