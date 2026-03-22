package cr.ac.una.proyecto_programacion_iv_bolsa_empleo;

import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.models.*;
import cr.ac.una.proyecto_programacion_iv_bolsa_empleo.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProyectoProgramacionIvBolsaEmpleoApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                ProyectoProgramacionIvBolsaEmpleoApplication.class, args);
    }

    @Bean
    CommandLineRunner cargarDatos(
            EmpresaService empresaService,
            OferenteService oferenteService,
            CaracteristicaService caracteristicaService,
            AdminService administradorService) {

        return args -> {
            if (!empresaService.obtenerTodas().isEmpty()) return;

            Administrador admin = new Administrador();
            admin.setIdentificacion("admin01");
            admin.setNombre("Administrador");
            admin.setClave("admin123");
            administradorService.guardar(admin);

            Empresa e = new Empresa();
            e.setNombre("JSV Inc.");
            e.setLocalizacion("Desamparados");
            e.setCorreo("jsv_inc@gmail.com");
            e.setTelefono("0000-0000");
            e.setDescripcion("JSV Inc.");
            e.setPassword("jsv_inc@gmail.com");
            e.setAprobado(true);
            empresaService.guardar(e);

            Oferente o = new Oferente();
            o.setIdentificacion("1111");
            o.setNombre("Isaac");
            o.setPrimerApellido("Naranjo");
            o.setNacionalidad("Costarricense");
            o.setTelefono("1111-1111");
            o.setCorreo("isaac.naranjo@gmail.com");
            o.setResidencia("Costa Rica");
            o.setPassword("1111");
            o.setAprobado(true);
            oferenteService.guardar(o);

            Caracteristica lenguajes = new Caracteristica();
            lenguajes.setNombre("Lenguajes de Programacion");
            caracteristicaService.guardar(lenguajes);

            Caracteristica java = new Caracteristica();
            java.setNombre("Java");
            java.setPadre(lenguajes);
            caracteristicaService.guardar(java);

            Caracteristica python = new Caracteristica();
            python.setNombre("Python");
            python.setPadre(lenguajes);
            caracteristicaService.guardar(python);

            Caracteristica techWeb = new Caracteristica();
            techWeb.setNombre("Tecnologias Web");
            caracteristicaService.guardar(techWeb);

            Caracteristica html = new Caracteristica();
            html.setNombre("HTML");
            html.setPadre(techWeb);
            caracteristicaService.guardar(html);

            Caracteristica css = new Caracteristica();
            css.setNombre("CSS");
            css.setPadre(techWeb);
            caracteristicaService.guardar(css);

            System.out.println(">>> Datos iniciales cargados");
        };
    }
}