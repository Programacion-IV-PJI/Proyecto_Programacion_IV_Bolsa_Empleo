package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nombre;
    private String localizacion;
    private String correo;
    private String telefono;
    private String descripcion;
    private String password;
    private boolean aprobado;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Puesto> puestos;

    public Long getId() { return id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getLocalizacion() { return localizacion; }
    public void setLocalizacion(String localizacion) { this.localizacion = localizacion; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isAprobado() { return aprobado; }
    public void setAprobado(boolean aprobado) { this.aprobado = aprobado; }

    public List<Puesto> getPuestos() { return puestos; }
    public void setPuestos(List<Puesto> puestos) { this.puestos = puestos; }
}