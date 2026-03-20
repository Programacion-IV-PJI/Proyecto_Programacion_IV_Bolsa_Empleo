package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.controllers;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.EmpresaService;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.OferenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private OferenteService oferenteService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/empresas-pendientes")
    public String empresasPendientes(Model model) {
        model.addAttribute("empresas", empresaService.obtenerTodas());
        return "admin/empresas-pendientes";
    }

    @GetMapping("/oferentes-pendientes")
    public String oferentesPendientes(Model model) {
        model.addAttribute("oferentes", oferenteService.obtenerTodos());
        return "admin/oferentes-pendientes";
    }

    @GetMapping("/caracteristicas")
    public String caracteristicas() {
        return "admin/caracteristicas";
    }

    @GetMapping("/reportes")
    public String reportes() {
        return "admin/reportes";
    }
}