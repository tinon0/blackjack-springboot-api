package ar.edu.utn.frc.tup.lciii.spring_boot_api_example.dtos.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchDto {
    private Long idPlayer;
    private String mensaje;
    private RoundDto round;
}
