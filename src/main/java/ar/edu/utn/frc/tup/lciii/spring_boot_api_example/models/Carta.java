package ar.edu.utn.frc.tup.lciii.spring_boot_api_example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Carta {
    private String valor;
    private String palo;
    private Integer numero;
}
