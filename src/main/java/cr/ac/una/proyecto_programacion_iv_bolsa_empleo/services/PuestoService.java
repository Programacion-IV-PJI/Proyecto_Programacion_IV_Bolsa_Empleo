package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Puesto;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.repositories.IPuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuestoService {

    @Autowired
    private IPuestoRepository puestoRepository;

    public List<Puesto> obtenerTodos(){
        return puestoRepository.findAll();
    }

    public Puesto guardar(Puesto puesto){
        return puestoRepository.save(puesto);
    }

    public List<Puesto> obtenerPublicos() {
        return puestoRepository.findByPublicoTrue();
    }

}
