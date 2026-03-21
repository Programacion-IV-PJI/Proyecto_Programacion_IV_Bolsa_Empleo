package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.repositories;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Empresa;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Puesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPuestoRepository extends JpaRepository<Puesto, Long> {
    List<Puesto> findTop5ByPublicoTrueAndActivoTrueOrderByIdDesc();
    List<Puesto> findByActivoTrue();
    List<Puesto> findByEmpresa(Empresa empresa);

    @Query("SELECT p FROM Puesto p JOIN p.caracteristicas r WHERE r.caracteristica.id IN :ids AND p.publico = true AND p.activo = true GROUP BY p HAVING COUNT(DISTINCT r.id) >= :total")
    List<Puesto> findByCaracteristicas(@Param("ids") List<Long> ids, @Param("total") Long total);

    @Query("SELECT p FROM Puesto p WHERE MONTH(p.fechaCreacion) = :mes AND YEAR(p.fechaCreacion) = :anio")
    List<Puesto> findByMesYAnio(@Param("mes") int mes, @Param("anio") int anio);
}
