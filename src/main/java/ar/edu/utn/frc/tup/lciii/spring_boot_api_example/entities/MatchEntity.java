package ar.edu.utn.frc.tup.lciii.spring_boot_api_example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "matches")
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "player_id")
    @ManyToOne
    private PlayerEntity player;

    @JoinColumn(name = "round_id")
    @OneToOne
    private RoundEntity round;
}
