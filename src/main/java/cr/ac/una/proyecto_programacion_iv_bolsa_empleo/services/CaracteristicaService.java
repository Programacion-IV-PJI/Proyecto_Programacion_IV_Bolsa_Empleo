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

}
