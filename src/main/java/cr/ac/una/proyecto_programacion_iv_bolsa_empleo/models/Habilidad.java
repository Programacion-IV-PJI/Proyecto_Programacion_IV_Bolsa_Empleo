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

    // GETTERS
    public Long getId() { return id; }

    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }

    public Caracteristica getCaracteristica() { return caracteristica; }
}
