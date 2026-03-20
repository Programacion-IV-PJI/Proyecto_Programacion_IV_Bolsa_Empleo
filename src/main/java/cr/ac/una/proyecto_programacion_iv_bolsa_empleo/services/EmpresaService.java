package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Empresa;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.repositories.IEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmpresaService {
    @Autowired
    private IEmpresaRepository empresaRepository;

    public List<Empresa> obtenerTodas(){
        return empresaRepository.findAll();
    }

    public Empresa guardar(Empresa empresa){
        return empresaRepository.save(empresa);
    }

    public Empresa obtenerPorId(Long id){
        return empresaRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id){
        empresaRepository.deleteById(id);
    }

}
