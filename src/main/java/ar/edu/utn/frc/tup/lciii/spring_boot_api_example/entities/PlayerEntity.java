package ar.edu.utn.frc.tup.lciii.spring_boot_api_example.entities;

import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.models.Carta;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "players")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cant_fichas")
    private Integer fichas;

    //private List<Carta> cartas;
}
