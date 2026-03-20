package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Puesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private Double salario;

    private boolean publico;
    private boolean activo;

    @ManyToOne
    private Empresa empresa;

    @OneToMany(mappedBy = "puesto")
    private List<RequisitoPuesto> caracteristicas;

    // ======================
    // GETTERS Y SETTERS
    // ======================

    public Long getId() { return id; }

    public boolean isPublico() { return publico; }
    public void setPublico(boolean publico) { this.publico = publico; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}