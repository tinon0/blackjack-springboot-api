package ar.edu.utn.frc.tup.lciii.spring_boot_api_example.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Match {
    private Player player;
    private Round round;
}
