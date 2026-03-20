package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models;
import jakarta.persistence.*;

@Entity
public class Caracteristica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    private Caracteristica padre;
}
