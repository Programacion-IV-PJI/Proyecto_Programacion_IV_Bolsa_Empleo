package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.controllers;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.PuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private PuestoService puestoService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "empresa/dashboard";
    }

    @GetMapping("/mis-puestos")
    public String misPuestos(Model model) {
        model.addAttribute("puestos", puestoService.obtenerTodos());
        return "empresa/mis-puestos";
    }

    @GetMapping("/publicar-puesto")
    public String publicarPuesto() {
        return "empresa/publicar-puesto";
    }

    @GetMapping("/candidatos")
    public String candidatos() {
        return "empresa/candidatos";
    }

    @GetMapping("/detalle-candidato")
    public String detalleCandidato() {
        return "empresa/detalle-candidato";
    }
}