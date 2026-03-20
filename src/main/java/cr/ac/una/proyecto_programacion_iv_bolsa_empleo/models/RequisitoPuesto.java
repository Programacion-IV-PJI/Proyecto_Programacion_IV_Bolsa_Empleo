package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models;

import jakarta.persistence.*;

@Entity

public class RequisitoPuesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int nivelRequerido;

    @ManyToOne
    private Puesto puesto;

    @ManyToOne
    private Caracteristica caracteristica;

    // GETTERS
    public Long getId() { return id; }

    public int getNivelRequerido() { return nivelRequerido; }

    public Caracteristica getCaracteristica() { return caracteristica; }
}
