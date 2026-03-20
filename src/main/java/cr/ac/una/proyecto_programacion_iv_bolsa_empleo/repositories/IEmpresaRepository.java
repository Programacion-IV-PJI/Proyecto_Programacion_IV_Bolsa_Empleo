package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.repositories;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmpresaRepository extends JpaRepository<Empresa, Long> {
}
