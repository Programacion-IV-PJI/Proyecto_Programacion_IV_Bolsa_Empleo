package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.controllers;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.PuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {

    @Autowired
    private PuestoService puestoService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("puestos", puestoService.obtenerPublicos());
        return "publico/index";
    }

    @GetMapping("/buscar")
    public String buscar() {
        return "publico/buscar";
    }

    @GetMapping("/login")
    public String login() {
        return "publico/login";
    }

    @GetMapping("/registro/empresa")
    public String registroEmpresa() {
        return "publico/registro-empresa";
    }

    @GetMapping("/registro/oferente")
    public String registroOferente() {
        return "publico/registro-oferente";
    }
}