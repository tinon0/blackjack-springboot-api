package ar.edu.utn.frc.tup.lciii.spring_boot_api_example.services;

import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.entities.CartaEntity;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.entities.RoundEntity;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.models.Carta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Stack;

@Service
public interface CartaService {
    Stack<Carta> crearMazo();
    void saveCartas(List<CartaEntity> cartas);
    boolean mayorA21(RoundEntity roundEntity);
    RoundEntity hasta18(RoundEntity roundEntity, Stack<CartaEntity> mazo);
}
