package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.controllers;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.*;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private PuestoService puestoService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private OferenteService oferenteService;

    @Autowired
    private CaracteristicaService caracteristicaService;

    @Autowired
    private RequisitoPuestoService requisitoPuestoService;

    // Dashboard
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Long id = (Long) session.getAttribute("id");
        Empresa empresa = empresaService.obtenerPorId(id);
        model.addAttribute("empresa", empresa);
        return "empresa/dashboard";
    }

    // Ver puestos (HTML usa /empresa/puestos)
    @GetMapping("/puestos")
    public String puestos(HttpSession session, Model model) {
        Long id = (Long) session.getAttribute("id");
        Empresa empresa = empresaService.obtenerPorId(id);
        model.addAttribute("empresa", empresa);
        model.addAttribute("puestos", puestoService.obtenerPorEmpresa(empresa));
        return "empresa/mis-puestos";
    }

    // Mostrar formulario (HTML usa /empresa/puestos/nuevo)
    @GetMapping("/puestos/nuevo")
    public String nuevoPuesto(HttpSession session, Model model) {
        model.addAttribute("puesto", new Puesto());
        model.addAttribute("caracteristicas", caracteristicaService.obtenerTodas());
        return "empresa/publicar-puesto";
    }

    // Guardar puesto (HTML usa /empresa/puestos/guardar)
    @PostMapping("/puestos/guardar")
    public String guardarPuesto(@ModelAttribute Puesto puesto, HttpSession session, @RequestParam(required = false) List<Long> caracteristicaIds, @RequestParam(required = false) List<Integer> niveles) {
        Long id = (Long) session.getAttribute("id");
        Empresa empresa = empresaService.obtenerPorId(id);
        puesto.setEmpresa(empresa);
        puesto.setActivo(true);
        puestoService.guardar(puesto);

        if (caracteristicaIds != null) {
            for (int i = 0; i < caracteristicaIds.size(); i++) {
                RequisitoPuesto req = new RequisitoPuesto();
                req.setPuesto(puesto);
                req.setCaracteristica(caracteristicaService.obtenerPorId(caracteristicaIds.get(i)));
                req.setNivelDeseado(niveles != null && i < niveles.size() ? niveles.get(i) : 1);
                requisitoPuestoService.guardar(req);
            }
        }
        return "redirect:/empresa/puestos";
    }

    // Buscar candidatos (HTML usa /empresa/candidatos/buscar)
    @GetMapping("/candidatos/buscar")
    public String buscarCandidatos(@RequestParam Long puestoId, Model model) {

        Puesto puesto = puestoService.obtenerPorId(puestoId);
        List<Oferente> todosOferentes = oferenteService.obtenerAprobados();
        List<CandidatoResultado> resultados = new ArrayList<>();
        List<RequisitoPuesto> requisitos = puesto.getCaracteristicas();

        for (Oferente oferente : todosOferentes) {
            if (requisitos.isEmpty()) {
                // Si no hay requisitos, mostrar todos con 100%
                resultados.add(new CandidatoResultado(oferente, 0, 0));
            } else {
                int cumplidos = 0;
                for (RequisitoPuesto req : requisitos) {
                    boolean cumple = oferente.getHabilidades().stream().anyMatch(h ->
                            h.getCaracteristica().getId().equals(req.getCaracteristica().getId()) &&
                                    h.getNivel() >= req.getNivelDeseado()
                    );
                    if (cumple) cumplidos++;
                }
                resultados.add(new CandidatoResultado(oferente, cumplidos, requisitos.size()));
            }
        }

        model.addAttribute("puesto", puesto);
        model.addAttribute("resultados", resultados);
        return "empresa/candidatos";
    }

    // Ver detalle candidato
    @GetMapping("/candidatos/{id}")
    public String detalleCandidato(@PathVariable Long id, Model model) {

        Oferente oferente = oferenteService.obtenerPorId(id);
        model.addAttribute("oferente", oferente);
        model.addAttribute("habilidades", oferente.getHabilidades());
        return "empresa/detalle-candidato";
    }

    // Desactivar puesto (HTML lo usa)
    @GetMapping("/puestos/desactivar/{id}")
    public String desactivarPuesto(@PathVariable Long id) {
        Puesto puesto = puestoService.obtenerPorId(id);
        if (puesto != null) {
            puesto.setActivo(false);
            puestoService.guardar(puesto);
        }
        return "redirect:/empresa/puestos?id=";
    }
}