package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.repositories;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Puesto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPuestoRepository extends JpaRepository<Puesto, Long> {
    List<Puesto> findByPublicoTrue();
    List<Puesto> findByActivoTrue();
    List<Puesto> findByPublicoTrueAndActivoTrue();
}
