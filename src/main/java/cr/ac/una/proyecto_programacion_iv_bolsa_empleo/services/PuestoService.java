package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Empresa;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Puesto;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.repositories.IPuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuestoService {

    @Autowired
    private IPuestoRepository puestoRepository;

    // OBTENER TODOS
    public List<Puesto> obtenerTodos(){

        return puestoRepository.findAll();
    }

    // GUARDAR
    public Puesto guardar(Puesto puesto){
        if (puesto.getId() == null) {
            puesto.setFechaCreacion(java.time.LocalDate.now());
        }
        return puestoRepository.save(puesto);
    }

    // OBTENER SOLO PUBLICOS Y ACTIVOS
    public List<Puesto> obtenerPublicos(){

        return puestoRepository.findTop5ByPublicoTrueAndActivoTrueOrderByIdDesc();
    }

    // OBTENER SOLO ACTIVOS
    public List<Puesto> obtenerActivos(){
        return puestoRepository.findByActivoTrue();
    }

    // OBTENER POR ID
    public Puesto obtenerPorId(Long id) {

        return puestoRepository.findById(id).orElse(null);
    }

    public List<Puesto> obtenerPorEmpresa(Empresa empresa) {
        return puestoRepository.findByEmpresa(empresa);
    }

    public List<Puesto> buscarPorCaracteristicas(List<Long> caracteristicaIds) {
        return puestoRepository.findByCaracteristicas(caracteristicaIds, (long) caracteristicaIds.size());
    }

    public List<Puesto> obtenerPorMesYAnio(int mes, int anio) {
        return puestoRepository.findByMesYAnio(mes, anio);
    }
}