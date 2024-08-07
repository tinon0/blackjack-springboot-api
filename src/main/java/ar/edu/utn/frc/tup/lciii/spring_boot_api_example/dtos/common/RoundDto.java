package ar.edu.utn.frc.tup.lciii.spring_boot_api_example.dtos.common;

import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.models.Carta;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoundDto {

    //@JsonFormat(shape = JsonFormat.Shape.ARRAY)
    private List<Carta> cartasPlayer;
    private Integer sumaPlayer;
    //@JsonFormat(shape = JsonFormat.Shape.ARRAY)
    private List<Carta> cartasApp;
    private Integer sumaApp;
}
