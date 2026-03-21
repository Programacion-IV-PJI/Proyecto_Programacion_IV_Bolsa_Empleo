package cr.ac.una.proyecto_programacion_iv_bolsa_empleo.controllers;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ArchivoController {

    @GetMapping("/uploads/cvs/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> servirCV(@PathVariable String filename) {
        try {
            Resource resource = new FileSystemResource("cvs/" + filename);
            if (!resource.exists()) return ResponseEntity.notFound().build();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}