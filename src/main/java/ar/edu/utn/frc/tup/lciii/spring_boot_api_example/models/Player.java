package ar.edu.utn.frc.tup.lciii.spring_boot_api_example.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Player {
    private Long id;
    private Integer fichas;
    private List<Carta> cartas;
}
