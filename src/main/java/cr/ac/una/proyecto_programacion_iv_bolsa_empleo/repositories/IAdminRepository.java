package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.repositories;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository extends JpaRepository<Administrador, Long> {
    Administrador findByIdentificacionAndClave(String identificacion, String clave);
}