package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.repositories;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEmpresaRepository extends JpaRepository<Empresa, Long> {
    List<Empresa> findByAprobadoFalse();
    Empresa findByCorreoAndPassword(String correo, String password);
}
