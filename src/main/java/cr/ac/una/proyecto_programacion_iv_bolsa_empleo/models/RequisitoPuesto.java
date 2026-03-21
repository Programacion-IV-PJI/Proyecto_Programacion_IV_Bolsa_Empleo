package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "requisito_puesto")
public class RequisitoPuesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nivel_requerido")
    private int nivelDeseado;

    @ManyToOne
    @JoinColumn(name = "puesto_id")
    private Puesto puesto;

    @ManyToOne
    @JoinColumn(name = "caracteristica_id")
    private Caracteristica caracteristica;


    public Long getId() { return id; }

    public int getNivelDeseado() { return nivelDeseado; }
    public void setNivelDeseado(int nivelDeseado) { this.nivelDeseado = nivelDeseado; }

    public Puesto getPuesto() { return puesto; }
    public void setPuesto(Puesto puesto) { this.puesto = puesto; }

    public Caracteristica getCaracteristica() { return caracteristica; }
    public void setCaracteristica(Caracteristica caracteristica) { this.caracteristica = caracteristica; }
}
