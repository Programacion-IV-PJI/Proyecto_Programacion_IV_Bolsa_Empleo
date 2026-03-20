package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.controllers;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.CaracteristicaService;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.EmpresaService;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.OferenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private OferenteService oferenteService;

    @Autowired
    private CaracteristicaService caracteristicaService;

    // Dashboard
    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }

    // ===============================
    // EMPRESAS
    // ===============================

    @GetMapping("/empresas/pendientes")
    public String empresasPendientes(Model model) {
        model.addAttribute("empresas", empresaService.obtenerTodas());
        return "admin/empresas-pendientes";
    }

    @GetMapping("/empresas/aprobar/{id}")
    public String aprobarEmpresa(@PathVariable Long id) {

        // 🔥 luego hacemos lógica real
        empresaService.aprobar(id);

        return "redirect:/admin/empresas/pendientes";
    }

    // ===============================
    // OFERENTES
    // ===============================

    @GetMapping("/oferentes/pendientes")
    public String oferentesPendientes(Model model) {
        model.addAttribute("oferentes", oferenteService.obtenerTodos());
        return "admin/oferentes-pendientes";
    }

    @GetMapping("/oferentes/aprobar/{id}")
    public String aprobarOferente(@PathVariable Long id) {

        // 🔥 luego lógica real
        oferenteService.aprobar(id);

        return "redirect:/admin/oferentes/pendientes";
    }

    // ===============================
    // CARACTERISTICAS
    // ===============================

    @GetMapping("/caracteristicas")
    public String caracteristicas(Model model) {

        model.addAttribute("subcategorias", caracteristicaService.obtenerTodas());

        return "admin/caracteristicas";
    }

    @PostMapping("/caracteristicas/crear")
    public String crearCaracteristica(
            @RequestParam String nombre,
            @RequestParam(required = false) Long padreId) {

        // 🔥 luego lógica real
        System.out.println("Crear: " + nombre + " padre: " + padreId);

        return "redirect:/admin/caracteristicas";
    }

    // ===============================
    // REPORTES
    // ===============================

    @GetMapping("/reportes")
    public String reportes() {
        return "admin/reportes";
    }

    @GetMapping("/reportes/generar")
    public String generarReporte(
            @RequestParam int mes,
            @RequestParam int anio) {

        // 🔥 luego PDF real
        System.out.println("Generar reporte: " + mes + "/" + anio);

        return "redirect:/admin/reportes";
    }
}