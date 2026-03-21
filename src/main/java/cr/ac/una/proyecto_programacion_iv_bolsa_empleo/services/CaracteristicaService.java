package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Caracteristica;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.repositories.ICaracteristicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaracteristicaService {

    @Autowired
    private ICaracteristicaRepository caracteristicaRepository;

    public List<Caracteristica> obtenerTodas(){
        return caracteristicaRepository.findAll();
    }

    public Caracteristica guardar(Caracteristica caracteristica){
        return caracteristicaRepository.save(caracteristica);
    }

    public Caracteristica obtenerPorId(Long id) {
        return caracteristicaRepository.findById(id).orElse(null);
    }

    public List<Caracteristica> obtenerRaices(){
        return caracteristicaRepository.findByPadreIsNull();
    }

    public List<Caracteristica> obtenerHijos(Long padreId){
        return caracteristicaRepository.findByPadreId(padreId);
    }


}
