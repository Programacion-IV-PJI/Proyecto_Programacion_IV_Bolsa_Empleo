package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.RequisitoPuesto;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.repositories.IRequisitoPuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequisitoPuestoService {
    @Autowired
    private IRequisitoPuestoRepository requisitoRepository;

    public List<RequisitoPuesto> obtenerTodas(){
        return requisitoRepository.findAll();
    }

    public RequisitoPuesto guardar(RequisitoPuesto requisitoPuesto){
        return requisitoRepository.save(requisitoPuesto);
    }

    public RequisitoPuesto obtenerPorId(Long id) {
        return requisitoRepository.findById(id).orElse(null);
    }

    public List<RequisitoPuesto> obtenerPorPuesto(Long puestoId){
        return requisitoRepository.findByPuestoId(puestoId);
    }

    public void eliminar(Long id) {
        requisitoRepository.deleteById(id);
    }
}
