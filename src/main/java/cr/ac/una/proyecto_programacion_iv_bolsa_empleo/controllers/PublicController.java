package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.controllers;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Empresa;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Oferente;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.EmpresaService;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.OferenteService;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.PuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PublicController {

    @Autowired
    private PuestoService puestoService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private OferenteService oferenteService;

    // INDEX
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("puestos", puestoService.obtenerPublicos());
        return "publico/index";
    }

    // LOGIN
    @GetMapping("/login")
    public String login() {
        return "publico/login";
    }

    // REGISTRO EMPRESA
    @GetMapping("/registro/empresa")
    public String registroEmpresa(Model model) {
        model.addAttribute("empresa", new Empresa());
        return "publico/registro-empresa";
    }

    @PostMapping("/registro/empresa")
    public String guardarEmpresa(@ModelAttribute Empresa empresa) {
        empresa.setAprobado(false); // 🔥 importante
        empresaService.guardar(empresa);
        return "redirect:/login";
    }

    // REGISTRO OFERENTE
    @GetMapping("/registro/oferente")
    public String registroOferente(Model model) {
        model.addAttribute("oferente", new Oferente());
        return "publico/registro-oferente";
    }

    @PostMapping("/registro/oferente")
    public String guardarOferente(@ModelAttribute Oferente oferente) {
        oferente.setAprobado(false);
        oferenteService.guardar(oferente);
        return "redirect:/login";
    }
}