package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models;

import jakarta.persistence.*;

@Entity
public class Habilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int nivel;

    @ManyToOne
    private Oferente oferente;

    @ManyToOne
    private Caracteristica caracteristica;
}
