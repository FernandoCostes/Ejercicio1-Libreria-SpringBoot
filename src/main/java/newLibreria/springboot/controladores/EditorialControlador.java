
package newLibreria.springboot.controladores;

import newLibreria.springboot.entidades.Editorial;
import newLibreria.springboot.servicios.EditorialServicio;
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
@RequestMapping("/editorial")
public class EditorialControlador {
    
    @Autowired
    private EditorialServicio editorialServicio;
    
    
       @GetMapping
    public ModelAndView mostrarTodosHabilitados() {
        ModelAndView mav = new ModelAndView("editorial-tabla");
        mav.addObject("editoriales", editorialServicio.mostrarEditorialesHabilitadas());
        mav.addObject("habilitados", true);
        return mav;
    }

    @GetMapping("/deshabilitados")
    public ModelAndView mostrarTodosDeshabilitados() {
        ModelAndView mav = new ModelAndView("editorial-tabla");
        mav.addObject("editoriales", editorialServicio.mostrarEditorialesDeshabilitadas());
        mav.addObject("habilitados", false);

        return mav;
    }
    
     @GetMapping("/crear")
    public ModelAndView crearEditorial(){
        ModelAndView mav = new ModelAndView("editorial-formulario");
        mav.addObject("editorial", new Editorial());
        mav.addObject("title", "Crear Editorial");
        mav.addObject("action","guardar" );
        return mav;
    }
    
    @GetMapping("/editar/{id}")
    public ModelAndView editarEditorial(@PathVariable String id){
        ModelAndView mav = new ModelAndView("editorial-formulario");
        mav.addObject("editorial", editorialServicio.buscarPorId(id));
        mav.addObject("title", "Editar Editorial");
        mav.addObject("action", "modificar");
        return mav;
    }
    
    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam String nombre){
        editorialServicio.crearEditorial(nombre);
        return new RedirectView("/editorial");
    }
    
    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam String id, String nombre){
        editorialServicio.modificarEditorial(id, nombre);
        return new RedirectView("/editorial");
    }
    
     @PostMapping("/baja/{id}")
    public RedirectView deshabilitar(@PathVariable String id) {
        editorialServicio.deshabilitar(id);
        return new RedirectView("/editorial");
    }
    
    @PostMapping("/alta/{id}")
    public RedirectView habilitar(@PathVariable String id) {
        editorialServicio.habilitar(id);
        return new RedirectView("/editorial");
    }
}
