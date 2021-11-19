package newLibreria.springboot.controladores;

import newLibreria.springboot.entidades.Autor;
import newLibreria.springboot.servicios.AutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/autor")
public class AutorControlador {

    @Autowired
    private AutorServicio autorServicio;

 

    @GetMapping
    public ModelAndView mostrarTodosHabilitados() {
        ModelAndView mav = new ModelAndView("autor-tabla");
        mav.addObject("autores", autorServicio.mostrarAutoresHabilitados());
        mav.addObject("habilitados", true);
        return mav;
    }

    @GetMapping("/deshabilitados")
    public ModelAndView mostrarTodosDeshabilitados() {
        ModelAndView mav = new ModelAndView("autor-tabla");
        mav.addObject("autores", autorServicio.mostrarAutoresDeshabilitados());
        mav.addObject("habilitados", false);

        return mav;
    }

    @GetMapping("/crear")
    public ModelAndView crearAutor() {
        ModelAndView mav = new ModelAndView("autor-formulario");
        mav.addObject("autor", new Autor());
        mav.addObject("title", "Crear Autor");
        mav.addObject("action", "guardar");
        return mav;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarAutor(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("autor-formulario");
        mav.addObject("autor", autorServicio.buscarPorId(id));
        mav.addObject("title", "Editar Autor");
        mav.addObject("action", "modificar");
        return mav;
    }

    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam String nombre) {
        autorServicio.crearAutor(nombre);
        return new RedirectView("/autor");
    }

    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam String id, @RequestParam String nombre) {
        autorServicio.modificarAutor(id, nombre);
        return new RedirectView("/autor");
    }

    @PostMapping("/baja/{id}")
    public RedirectView deshabilitar(@PathVariable String id) {
        autorServicio.deshabilitar(id);
        return new RedirectView("/autor");
    }
    
    @PostMapping("/alta/{id}")
    public RedirectView habilitar(@PathVariable String id) {
        autorServicio.habilitar(id);
        return new RedirectView("/autor");
    }

}
