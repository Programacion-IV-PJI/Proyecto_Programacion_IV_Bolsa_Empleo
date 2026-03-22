package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.controllers;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Administrador;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Empresa;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Oferente;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.AdminService;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.CaracteristicaService;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.EmpresaService;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.OferenteService;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.PuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.servlet.http.HttpSession;

@Controller
public class PublicController {

    @Autowired
    private PuestoService puestoService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private OferenteService oferenteService;

    @Autowired
    private CaracteristicaService caracteristicaService;

    @Autowired
    private AdminService administradorService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("puestos", puestoService.obtenerPublicos());
        return "publico/index";
    }

    @GetMapping("/login")
    public String login() {
        return "publico/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String usuario,
                        @RequestParam String clave,
                        HttpSession session,
                        Model model) {
        Empresa empresa = empresaService.buscarPorUsuarioYClave(usuario, clave);
        if (empresa != null && empresa.isAprobado()) {
            session.setAttribute("id", empresa.getId());
            session.setAttribute("correo", empresa.getCorreo());
            session.setAttribute("rol", "empresa");
            return "redirect:/empresa/dashboard";
        }

        Oferente oferente = oferenteService.buscarPorUsuarioYClave(usuario, clave);
        if (oferente != null && oferente.isAprobado()) {
            session.setAttribute("id", oferente.getId());
            session.setAttribute("correo", oferente.getCorreo());
            session.setAttribute("rol", "oferente");
            return "redirect:/oferente/dashboard";
        }

        Administrador admin = administradorService.buscarPorCredenciales(usuario, clave);
        if (admin != null) {
            session.setAttribute("id", admin.getId());
            session.setAttribute("correo", admin.getIdentificacion());
            session.setAttribute("rol", "admin");
            return "redirect:/admin/dashboard";
        }

        model.addAttribute("error", "Credenciales incorrectas o cuenta no aprobada");
        return "publico/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/registro/empresa")
    public String registroEmpresa(Model model) {
        model.addAttribute("empresa", new Empresa());
        return "publico/registro-empresa";
    }

    @PostMapping("/registro/empresa")
    public String guardarEmpresa(@ModelAttribute Empresa empresa) {
        empresa.setAprobado(false);
        if (empresa.getPassword() == null || empresa.getPassword().isEmpty()) {
            empresa.setPassword(empresa.getCorreo());
        }
        empresaService.guardar(empresa);
        return "redirect:/login";
    }

    @GetMapping("/registro/oferente")
    public String registroOferente(Model model) {
        model.addAttribute("oferente", new Oferente());
        return "publico/registro-oferente";
    }

    @PostMapping("/registro/oferente")
    public String guardarOferente(@ModelAttribute Oferente oferente) {
        oferente.setAprobado(false);
        if (oferente.getPassword() == null || oferente.getPassword().isEmpty()) {
            oferente.setPassword(oferente.getIdentificacion());
        }
        oferenteService.guardar(oferente);
        return "redirect:/login";
    }

    @GetMapping("/buscar")
    public String buscarPuestos(Model model) {
        model.addAttribute("caracteristicas", caracteristicaService.obtenerTodas());
        return "publico/buscar";
    }

    @PostMapping("/buscar")
    public String buscarPuestosPost(@RequestParam(required = false) List<Long> caracteristicaIds,
                                    Model model) {
        model.addAttribute("caracteristicas", caracteristicaService.obtenerTodas());
        if (caracteristicaIds != null && !caracteristicaIds.isEmpty()) {
            model.addAttribute("puestos", puestoService.buscarPorCaracteristicas(caracteristicaIds));
        }
        return "publico/buscar";
    }
}