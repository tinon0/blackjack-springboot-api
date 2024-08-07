package ar.edu.utn.frc.tup.lciii.spring_boot_api_example.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Stack;

@Getter
@Setter
@NoArgsConstructor
public class Round {
    private List<Carta> cartasPlayer;
    private List<Carta> cartasApp;
    private Stack<Carta> mazo;
    private boolean finished;
    private String winner;
}
