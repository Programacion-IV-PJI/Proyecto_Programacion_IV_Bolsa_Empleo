package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models;
import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name = "caracteristica")
public class Caracteristica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "padre_id")
    private Caracteristica padre;

    @OneToMany(mappedBy = "padre", cascade = CascadeType.ALL)
    private List<Caracteristica> hijos;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Caracteristica getPadre() { return padre; }
    public void setPadre(Caracteristica padre) { this.padre = padre; }

    public List<Caracteristica> getHijos() { return hijos; }
    public void setHijos(List<Caracteristica> hijos) { this.hijos = hijos; }


    public boolean esRaiz() {
        return padre == null;
    }

}
