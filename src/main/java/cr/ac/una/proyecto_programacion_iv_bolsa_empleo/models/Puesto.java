package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models;

import jakarta.persistence.*;
import java.util.List;
import java.time.LocalDate;

@Entity
@Table(name = "puesto")
public class Puesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private Double salario;

    private boolean publico;
    private boolean activo;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @OneToMany(mappedBy = "puesto", cascade = CascadeType.ALL)
    private List<RequisitoPuesto> caracteristicas;

    public Long getId() { return id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getSalario() { return salario; }
    public void setSalario(Double salario) { this.salario = salario; }

    public boolean isPublica() { return publico; }
    public void setPublica(boolean publica) { this.publico = publica; }

    public boolean isActiva() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }

    public List<RequisitoPuesto> getCaracteristicas() { return caracteristicas; }
    public void setCaracteristicas(List<RequisitoPuesto> caracteristicas) { this.caracteristicas = caracteristicas; }

}