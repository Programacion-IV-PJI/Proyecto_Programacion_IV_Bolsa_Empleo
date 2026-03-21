package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Oferente;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.repositories.IOferenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OferenteService {

    @Autowired
    private IOferenteRepository oferenteRepository;

    public List<Oferente> obtenerTodos(){

        return oferenteRepository.findAll();
    }

    public List<Oferente> obtenerPendientes() {
        return oferenteRepository.findByAprobadoFalse();
    }

    public List<Oferente> obtenerAprobados() {
        return oferenteRepository.findByAprobadoTrue();
    }

    public Oferente guardar(Oferente oferente){

        return oferenteRepository.save(oferente);
    }

    public Oferente obtenerPorId(Long id){

        return oferenteRepository.findById(id).orElse(null);
    }

    public void aprobar(Long id) {
        Oferente o = obtenerPorId(id);
        if (o != null) {
            o.setAprobado(true);
            oferenteRepository.save(o);
        }
    }

    public Oferente buscarPorUsuarioYClave(String usuario, String clave) {
        return oferenteRepository.findByCorreoAndPassword(usuario, clave);
    }
}
