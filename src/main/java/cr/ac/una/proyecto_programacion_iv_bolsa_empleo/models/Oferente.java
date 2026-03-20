package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models;
import jakarta.persistence.*;

@Entity
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

    private String cvPath; // 🔥 IMPORTANTE

    public Long getId() { return id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public boolean isAprobado() { return aprobado; }
    public void setAprobado(boolean aprobado) { this.aprobado = aprobado; }

    public String getCvPath() { return cvPath; }
    public void setCvPath(String cvPath) { this.cvPath = cvPath; }
}