package com.mlag.SBootEjemplo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
    esta clase hace llamados a otras paginas web y regresa
    el resultado a quien lo solicito
 */

@Controller
public class ContactoController {

    @Autowired
    private ContactoRepository contactoRepository;

    @GetMapping     // esto hace llamado a la raiz
    public String index(Model model) {
        List<Contacto> lista = contactoRepository.findAll();
        model.addAttribute("usuarios", lista);
        return "index";
    }

    @GetMapping("/contactos")
    //@ResponseBody
    public String obtenerContactos(Model model){
        //return contactoRepository.findAll();
        List<Contacto> lista = contactoRepository.findAll();
        for (Contacto contacto : lista) {
            System.out.println("Nombre: " + contacto.getNombre());
            System.out.println("Fecha de Nacimiento: " + contacto.getFechaNacimiento());
            System.out.println("Fecha de Registro: " + contacto.getFechaRegistro());
        }
        System.out.println("Registros recuperados: " + lista);
        model.addAttribute("usuarios", lista);

        return "contactos";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("contacto", new Contacto());
        return "form";
    }

    @PostMapping("/nuevo")
    public String guardarContacto(
            @RequestParam("nombre") String nombre,
            @RequestParam("fechaNacimiento") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaNacimiento) {
        Contacto contacto = new Contacto();
        contacto.setNombre(nombre);
        System.out.println("nombre ->" + nombre);
        contacto.setFechaNacimiento(fechaNacimiento);
        contacto.setFechaRegistro(LocalDateTime.now());

        contactoRepository.save(contacto);

        return "redirect:/";
    }

    @GetMapping("/ejemplo")
    public String mostrarPaginaInicio(Model model) {
        model.addAttribute("nombreUsuario", "María");
        /*List<Contacto> lista = new ArrayList<>();
        lista.add(new Contacto("Juan"));
        lista.add(new Contacto("María"));
        lista.add(new Contacto("Pedro"));
        model.addAttribute("usuarios", lista);*/
        List<Contacto> lista = contactoRepository.findAll();
        for (Contacto contacto : lista) {
            System.out.println("Nombre: " + contacto.getNombre());
            System.out.println("Fecha de Nacimiento: " + contacto.getFechaNacimiento());
            System.out.println("Fecha de Registro: " + contacto.getFechaRegistro());
        }
        System.out.println("Registros recuperados: " + lista);
        model.addAttribute("usuarios", lista);

        return "ejemplo";
    }
}
