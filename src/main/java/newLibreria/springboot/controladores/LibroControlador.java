
package newLibreria.springboot.controladores;

import newLibreria.springboot.entidades.Libro;
import newLibreria.springboot.servicios.AutorServicio;
import newLibreria.springboot.servicios.EditorialServicio;
import newLibreria.springboot.servicios.LibroServicio;
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
@RequestMapping("/libro")
public class LibroControlador {
    
    @Autowired
    private LibroServicio libroServicio;
    
    @Autowired
    private EditorialServicio editorialServicio;
    
    @Autowired
    private AutorServicio autorServicio;
    
    @GetMapping
    public ModelAndView mostrarTodos(){
        ModelAndView mav = new ModelAndView("libro-tabla");
        mav.addObject("libros", libroServicio.buscarTodos());
        return mav;
    }
    
    @GetMapping("/crear")
    public ModelAndView crearLibro(){
        ModelAndView mav = new ModelAndView("libro-formulario");
        mav.addObject("libro", new Libro() );
        mav.addObject("editoriales", editorialServicio.buscarTodos());
        mav.addObject("autores", autorServicio.buscarTodos());
        mav.addObject("title", "Crear Libro");
        mav.addObject("action", "guardar");
        return mav;
    }
    
    @GetMapping("/editar/{isbn}")
    public ModelAndView editarLibro(@PathVariable Long isbn){
        ModelAndView mav = new ModelAndView("libro-formulario");
        mav.addObject("libro", libroServicio.buscarPorId(isbn));
        mav.addObject("editoriales", editorialServicio.buscarTodos());
        mav.addObject("autores", autorServicio.buscarTodos());
        mav.addObject("title", "Editar Libro");
        mav.addObject("action", "modificar");
        return mav; 
    }
    
    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam Long isbn, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam("autor") String autorId, @RequestParam("editorial") String editorialId){
        libroServicio.crearLibro(isbn, titulo, anio, ejemplares, autorId, editorialId);
        return new RedirectView("/libro");
    }
    
    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam Long isbn, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam("autor") String autorId, @RequestParam("editorial") String editorialId){
        libroServicio.modificarLibro(isbn, titulo, anio, ejemplares, ejemplares, ejemplares, autorId, editorialId);
        return new RedirectView("/libro");
    }
    
    
    
    
}
