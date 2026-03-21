package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "habilidad")
public class Habilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int nivel;

    @ManyToOne
    @JoinColumn(name = "oferente_id")
    private Oferente oferente;

    @ManyToOne
    @JoinColumn(name = "caracteristica_id")
    private Caracteristica caracteristica;


    public Long getId() { return id; }

    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }

    public Oferente getOferente() { return oferente; }
    public void setOferente(Oferente oferente) { this.oferente = oferente; }

    public Caracteristica getCaracteristica() { return caracteristica;}
    public void setCaracteristica(Caracteristica caracteristica) { this.caracteristica = caracteristica; }

}
