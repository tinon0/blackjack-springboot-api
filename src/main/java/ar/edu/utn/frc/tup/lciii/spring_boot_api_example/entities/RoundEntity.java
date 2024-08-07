    package ar.edu.utn.frc.tup.lciii.spring_boot_api_example.entities;

    import ar.edu.utn.frc.tup.lciii.spring_boot_api_example.models.Carta;
    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.util.List;
    import java.util.Stack;

    @Setter
    @Getter
    @NoArgsConstructor
    @Entity
    @Table(name = "rounds")
    public class RoundEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Long id;

    //    @JoinColumn(name = "match_id")
    //    @ManyToOne
    //    private MatchEntity matchEntity;

        @OneToMany
        private List<CartaEntity> cartasPlayer;

        @OneToMany
        private List<CartaEntity> cartasApp;

        @OneToMany
        private List<CartaEntity> mazo; //con stack no le gusta

        private boolean finished;

        @Column(nullable = true)
        private String winner;
    }
