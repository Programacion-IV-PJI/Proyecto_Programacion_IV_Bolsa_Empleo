package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Administrador;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.repositories.IAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private IAdminRepository adminRepository;

    public Administrador buscarPorCredenciales(String identificacion, String clave) {
        return adminRepository.findByIdentificacionAndClave(identificacion, clave);
    }

    public Administrador guardar(Administrador admin) {
        return adminRepository.save(admin);
    }

    public List<Administrador> obtenerTodos() {
        return adminRepository.findAll();
    }
}