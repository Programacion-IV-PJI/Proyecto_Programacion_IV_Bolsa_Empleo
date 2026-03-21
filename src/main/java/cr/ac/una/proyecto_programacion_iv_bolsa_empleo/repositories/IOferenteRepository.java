package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.repositories;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Oferente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOferenteRepository extends JpaRepository<Oferente, Long> {
    List<Oferente> findByAprobadoFalse();
    List<Oferente> findByAprobadoTrue();
    Oferente findByCorreoAndPassword(String correo, String password);
}
