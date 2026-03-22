package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.controllers;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Caracteristica;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.Puesto;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.CaracteristicaService;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.EmpresaService;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.OferenteService;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.PuestoService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private OferenteService oferenteService;

    @Autowired
    private CaracteristicaService caracteristicaService;

    @Autowired
    private PuestoService puestoService;

    private String verificarAdmin(HttpSession session) {
        String rol = (String) session.getAttribute("rol");
        if (rol == null || !rol.equals("admin")) return "redirect:/login";
        return null;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        String redirect = verificarAdmin(session);
        if (redirect != null) return redirect;
        model.addAttribute("totalEmpresas", empresaService.obtenerTodas().size());
        model.addAttribute("totalOferentes", oferenteService.obtenerTodos().size());
        return "admin/dashboard";
    }

    @GetMapping("/empresas/pendientes")
    public String empresasPendientes(HttpSession session, Model model) {
        String redirect = verificarAdmin(session);
        if (redirect != null) return redirect;
        model.addAttribute("empresas", empresaService.obtenerPendientes());
        return "admin/empresas-pendientes";
    }

    @GetMapping("/empresas/aprobar/{id}")
    public String aprobarEmpresa(@PathVariable Long id, HttpSession session) {
        String redirect = verificarAdmin(session);
        if (redirect != null) return redirect;
        empresaService.aprobar(id);
        var e = empresaService.obtenerPorId(id);
        if (e.getPassword() == null || e.getPassword().isEmpty()) {
            e.setPassword(e.getCorreo());
            empresaService.guardar(e);
        }
        return "redirect:/admin/empresas/pendientes";
    }

    @GetMapping("/oferentes/pendientes")
    public String oferentesPendientes(HttpSession session, Model model) {
        String redirect = verificarAdmin(session);
        if (redirect != null) return redirect;
        model.addAttribute("oferentes", oferenteService.obtenerPendientes());
        return "admin/oferentes-pendientes";
    }

    @GetMapping("/oferentes/aprobar/{id}")
    public String aprobarOferente(@PathVariable Long id, HttpSession session) {
        String redirect = verificarAdmin(session);
        if (redirect != null) return redirect;
        oferenteService.aprobar(id);
        var o = oferenteService.obtenerPorId(id);
        if (o.getPassword() == null || o.getPassword().isEmpty()) {
            o.setPassword(o.getIdentificacion());
            oferenteService.guardar(o);
        }
        return "redirect:/admin/oferentes/pendientes";
    }

    @GetMapping("/caracteristicas")
    public String caracteristicas(@RequestParam(required = false) Long actualId,
                                  HttpSession session, Model model) {
        String redirect = verificarAdmin(session);
        if (redirect != null) return redirect;

        List<Caracteristica> ruta = new java.util.ArrayList<>();
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
        model.addAttribute("posiblesPadres", caracteristicaService.obtenerTodas());
        return "admin/caracteristicas";
    }

    @PostMapping("/caracteristicas/crear")
    public String crearCaracteristica(@RequestParam String nombre,
                                      @RequestParam(required = false) Long padreId,
                                      @RequestParam(required = false) Long actualId,
                                      HttpSession session) {
        String redirect = verificarAdmin(session);
        if (redirect != null) return redirect;

        Caracteristica c = new Caracteristica();
        c.setNombre(nombre);
        if (padreId != null) {
            c.setPadre(caracteristicaService.obtenerPorId(padreId));
        }
        caracteristicaService.guardar(c);
        if (actualId != null) {
            return "redirect:/admin/caracteristicas?actualId=" + actualId;
        }
        return "redirect:/admin/caracteristicas";
    }

    @GetMapping("/reportes")
    public String reportes(HttpSession session, Model model) {
        String redirect = verificarAdmin(session);
        if (redirect != null) return redirect;
        model.addAttribute("anioActual", java.time.LocalDate.now().getYear());
        return "admin/reportes";
    }

    @GetMapping("/reportes/generar")
    public void generarReporte(@RequestParam int mes,
                               @RequestParam int anio,
                               HttpSession session,
                               HttpServletResponse response) throws IOException {
        String rol = (String) session.getAttribute("rol");
        if (rol == null || !rol.equals("admin")) {
            response.sendRedirect("/login");
            return;
        }

        List<Puesto> puestos = puestoService.obtenerPorMesYAnio(mes, anio);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition",
                "attachment; filename=reporte_" + mes + "_" + anio + ".pdf");

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Font normalFont = new Font(Font.HELVETICA, 12);

            document.add(new Paragraph("Reporte de Puestos - " + mes + "/" + anio, titleFont));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Total de puestos: " + puestos.size(), normalFont));
            document.add(new Paragraph(" "));

            for (Puesto p : puestos) {
                document.add(new Paragraph(
                        "- " + p.getDescripcion() +
                                " | Empresa: " + p.getEmpresa().getNombre() +
                                " | Salario: " + p.getSalario(), normalFont));
            }

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}