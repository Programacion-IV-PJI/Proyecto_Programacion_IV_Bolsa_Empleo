package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.repositories;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.RequisitoPuesto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRequisitoPuestoRepository extends JpaRepository<RequisitoPuesto, Long> {
    List<RequisitoPuesto> findByPuestoId(Long puestoId);
}
