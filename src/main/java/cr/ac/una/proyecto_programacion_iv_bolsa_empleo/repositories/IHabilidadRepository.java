package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.repositories;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Habilidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHabilidadRepository extends JpaRepository<Habilidad, Long>{
    List<Habilidad> findByOferenteId(Long oferenteId);
}
