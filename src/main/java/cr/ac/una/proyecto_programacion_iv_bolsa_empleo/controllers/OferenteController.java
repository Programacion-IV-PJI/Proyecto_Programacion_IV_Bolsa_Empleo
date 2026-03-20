package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.controllers;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Oferente;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.HabilidadService;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Habilidad;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.OferenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/oferente")
public class OferenteController {

    @Autowired
    private OferenteService oferenteService;

    @Autowired
    private HabilidadService habilidadService;

    // 🔥 SIMULACIÓN DE USUARIO LOGUEADO
    private Long usuarioId = 1L;

    // =========================
    // DASHBOARD
    // =========================
    @GetMapping("/dashboard")
    public String dashboard() {
        return "oferente/dashboard";
    }

    // =========================
    // HABILIDADES
    // =========================
    @GetMapping("/habilidades")
    public String habilidades(
            @RequestParam(required = false) Long actualId,
            Model model) {

        // 🔥 luego conectamos con DB real
        model.addAttribute("misHabilidades", null);
        model.addAttribute("ruta", null);
        model.addAttribute("actual", null);
        model.addAttribute("subcategorias", null);
        model.addAttribute("todasCaracteristicas", null);

        return "oferente/habilidades";
    }

    @PostMapping("/habilidades/agregar")
    public String agregarHabilidad(
            @RequestParam Long caracteristicaId,
            @RequestParam int nivel) {

        Habilidad h = new Habilidad();
        h.setNivel(nivel);

        // 🔥 luego asignamos oferente y caracteristica
        habilidadService.guardar(h);

        return "redirect:/oferente/habilidades";
    }

    // =========================
    // CV
    // =========================
    @GetMapping("/mi-cv")
    public String miCV(Model model) {

        Oferente oferente = oferenteService.obtenerPorId(usuarioId);

        model.addAttribute("oferente", oferente);

        return "oferente/mi-cv";
    }

    @PostMapping("/subir-cv")
    public String subirCV(@RequestParam("archivo") MultipartFile archivo) {

        System.out.println("Archivo recibido: " + archivo.getOriginalFilename());

        // 🔥 luego guardamos archivo real

        return "redirect:/oferente/mi-cv";
    }
}