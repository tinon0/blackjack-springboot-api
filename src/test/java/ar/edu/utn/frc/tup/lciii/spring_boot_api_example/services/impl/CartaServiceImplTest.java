package ar.edu.utn.frc.tup.lciii.spring_boot_api_example.services.impl;

import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.models.Carta;
import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.services.CartaService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;
class CartaServiceImplTest {
    private CartaService service;
    public CartaServiceImplTest(){
        service = new CartaServiceImpl();
    }
    @Test
    void crearMazo(){
        Stack<Carta> mazo = service.crearMazo();
        assertEquals(52, mazo.size());
        for (Carta carta : mazo){
            System.out.println(carta.getValor() + "/ " + carta.getPalo() + "/ " + carta.getNumero());
        }
    }
}