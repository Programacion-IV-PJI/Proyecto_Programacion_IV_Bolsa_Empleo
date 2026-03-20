package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models;
import jakarta.persistence.*;

@Entity
public class Oferente {
    @Id
    private int identificación;
    private String nombre;
    private String apellido;
    private String nacionalidad;
    private String telefono;
    private String correo;
    private String residencia;
    private String password;
    private boolean aprobado;

}
