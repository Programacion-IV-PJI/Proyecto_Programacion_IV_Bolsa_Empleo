package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.controllers;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Puesto;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.PuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private PuestoService puestoService;

    // Dashboard
    @GetMapping("/dashboard")
    public String dashboard() {
        return "empresa/dashboard";
    }

    // Ver puestos (HTML usa /empresa/puestos)
    @GetMapping("/puestos")
    public String puestos(Model model) {
        model.addAttribute("puestos", puestoService.obtenerActivos());
        return "empresa/mis-puestos";
    }

    // Mostrar formulario (HTML usa /empresa/puestos/nuevo)
    @GetMapping("/puestos/nuevo")
    public String nuevoPuesto(Model model) {
        model.addAttribute("puesto", new Puesto());
        return "empresa/publicar-puesto";
    }

    // Guardar puesto (HTML usa /empresa/puestos/guardar)
    @PostMapping("/puestos/guardar")
    public String guardarPuesto(@ModelAttribute Puesto puesto) {
        puestoService.guardar(puesto);
        return "redirect:/empresa/puestos";
    }

    // Buscar candidatos (HTML usa /empresa/candidatos/buscar)
    @GetMapping("/candidatos/buscar")
    public String buscarCandidatos(@RequestParam Long puestoId, Model model) {

        // 🔥 Por ahora vacío (luego hacemos lógica real)
        model.addAttribute("puesto", new Puesto());
        model.addAttribute("resultados", null);

        return "empresa/candidatos";
    }

    // Ver detalle candidato
    @GetMapping("/candidatos/{id}")
    public String detalleCandidato(@PathVariable String id, Model model) {

        // 🔥 Luego conectamos con oferente real
        model.addAttribute("oferente", null);
        model.addAttribute("habilidades", null);

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

        return "redirect:/empresa/puestos";
    }
}