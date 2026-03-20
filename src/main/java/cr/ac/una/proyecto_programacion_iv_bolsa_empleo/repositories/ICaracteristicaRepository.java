package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.repositories;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Caracteristica;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ICaracteristicaRepository extends JpaRepository<Caracteristica, Long> {
    List<Caracteristica> findByPadreIsNull();
    List<Caracteristica> findByPadreId(Long padreId);
}
