package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "oferente")
public class Oferente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String primerApellido;
    private String nacionalidad;
    private String telefono;
    private String correo;
    private String residencia;
    private String password;
    private String identificacion;
    private boolean aprobado;

    private String cvPath;

    @OneToMany(mappedBy = "oferente", cascade = CascadeType.ALL)
    private List<Habilidad> habilidades;

    public Long getId() { return id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPrimerApellido() { return primerApellido; }
    public void setPrimerApellido(String primerApellido) { this.primerApellido = primerApellido; }

    public String getNombreCompleto() { return nombre + " " + primerApellido; }

    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getResidencia() { return residencia; }
    public void setResidencia(String residencia) { this.residencia = residencia; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    public boolean isAprobado() { return aprobado; }
    public void setAprobado(boolean aprobado) { this.aprobado = aprobado; }

    public String getCvPath() { return cvPath; }
    public void setCvPath(String cvPath) { this.cvPath = cvPath; }

    public List<Habilidad> getHabilidades() { return habilidades; }
    public void setHabilidades(List<Habilidad> habilidades) { this.habilidades = habilidades; }
}