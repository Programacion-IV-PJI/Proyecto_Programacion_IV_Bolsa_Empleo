package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.controllers;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.HabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oferente")
public class OferenteController {

    @Autowired
    private HabilidadService habilidadService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "oferente/dashboard";
    }

    @GetMapping("/habilidades")
    public String habilidades(Model model) {
        model.addAttribute("habilidades", habilidadService.obtenerTodas());
        return "oferente/habilidades";
    }

    @GetMapping("/mi-cv")
    public String miCV() {
        return "oferente/mi-cv";
    }
}