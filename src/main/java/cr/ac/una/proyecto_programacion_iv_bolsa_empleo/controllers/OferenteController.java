package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.controllers;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Caracteristica;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Oferente;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.HabilidadService;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Habilidad;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.OferenteService;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.CaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/oferente")
public class OferenteController {

    @Autowired
    private OferenteService oferenteService;

    @Autowired
    private HabilidadService habilidadService;

    @Autowired
    private CaracteristicaService caracteristicaService;

    private String verificarOferente(HttpSession session) {
        String rol = (String) session.getAttribute("rol");
        if (rol == null || !rol.equals("oferente")) return "redirect:/login";
        return null;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        String redirect = verificarOferente(session);
        if (redirect != null) return redirect;
        Long id = (Long) session.getAttribute("id");
        Oferente oferente = oferenteService.obtenerPorId(id);
        model.addAttribute("oferente", oferente);
        return "oferente/dashboard";
    }

    @GetMapping("/habilidades")
    public String habilidades(HttpSession session,
                              @RequestParam(required = false) Long actualId,
                              Model model) {
        String redirect = verificarOferente(session);
        if (redirect != null) return redirect;
        Long id = (Long) session.getAttribute("id");
        Oferente oferente = oferenteService.obtenerPorId(id);
        model.addAttribute("oferente", oferente);
        model.addAttribute("misHabilidades", oferente.getHabilidades());

        List<Caracteristica> ruta = new ArrayList<>();
        Caracteristica actual = null;
        List<Caracteristica> subcategorias;

        if (actualId != null) {
            actual = caracteristicaService.obtenerPorId(actualId);
            Caracteristica temp = actual;
            while (temp != null) {
                ruta.add(0, temp);
                temp = temp.getPadre();
            }
            subcategorias = actual.getHijos();
        } else {
            subcategorias = caracteristicaService.obtenerRaices();
        }

        model.addAttribute("ruta", ruta);
        model.addAttribute("actual", actual);
        model.addAttribute("subcategorias", subcategorias);
        model.addAttribute("todasCaracteristicas", caracteristicaService.obtenerTodas());

        return "oferente/habilidades";
    }

    @PostMapping("/habilidades/agregar")
    public String agregarHabilidad(HttpSession session,
                                   @RequestParam Long caracteristicaId,
                                   @RequestParam int nivel) {
        String redirect = verificarOferente(session);
        if (redirect != null) return redirect;
        Long id = (Long) session.getAttribute("id");
        Oferente oferente = oferenteService.obtenerPorId(id);
        Caracteristica caracteristica = caracteristicaService.obtenerPorId(caracteristicaId);

        boolean yaExiste = oferente.getHabilidades().stream()
                .anyMatch(h -> h.getCaracteristica().getId().equals(caracteristicaId));

        if (!yaExiste) {
            Habilidad h = new Habilidad();
            h.setNivel(nivel);
            h.setOferente(oferente);
            h.setCaracteristica(caracteristica);
            habilidadService.guardar(h);
        }

        return "redirect:/oferente/habilidades";
    }

    @GetMapping("/mi-cv")
    public String miCV(HttpSession session, Model model) {
        String redirect = verificarOferente(session);
        if (redirect != null) return redirect;
        Long id = (Long) session.getAttribute("id");
        Oferente oferente = oferenteService.obtenerPorId(id);
        model.addAttribute("oferente", oferente);
        return "oferente/mi-cv";
    }

    @PostMapping("/subir-cv")
    public String subirCV(HttpSession session,
                          @RequestParam("archivo") MultipartFile archivo) {
        String redirect = verificarOferente(session);
        if (redirect != null) return redirect;
        Long id = (Long) session.getAttribute("id");
        try {
            String carpeta = "cvs/";
            File dir = new File(carpeta);
            if (!dir.exists()) dir.mkdirs();
            Oferente oferente = oferenteService.obtenerPorId(id);
            String nombreArchivo = oferente.getId() + "_cv.pdf";
            Path ruta = Paths.get(carpeta + nombreArchivo);
            Files.write(ruta, archivo.getBytes());

            oferente.setCvPath(carpeta + nombreArchivo);
            oferenteService.guardar(oferente);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/oferente/mi-cv";
    }

    @GetMapping("/uploads/cvs/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> servirCV(@PathVariable String filename) {
        try {
            Resource resource = new FileSystemResource("cvs/" + filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}