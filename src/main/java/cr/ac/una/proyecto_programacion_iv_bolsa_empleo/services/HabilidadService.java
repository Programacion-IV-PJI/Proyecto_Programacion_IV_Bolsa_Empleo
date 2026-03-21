package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Habilidad;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.repositories.IHabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabilidadService {
    @Autowired
    private IHabilidadRepository habilidadRepository;

    public List<Habilidad> obtenerTodas(){

        return habilidadRepository.findAll();
    }

    public Habilidad guardar(Habilidad habilidad){

        return habilidadRepository.save(habilidad);
    }

    public Habilidad obtenerPorId(Long id) {
        return habilidadRepository.findById(id).orElse(null);
    }

    public List<Habilidad> obtenerPorOferente(Long oferenteId){

        return habilidadRepository.findByOferenteId(oferenteId);
    }

    public void eliminar(Long id) {
        habilidadRepository.deleteById(id);
    }

}
