
package newLibreria.springboot.servicios;

import java.util.List;
import java.util.Optional;
import newLibreria.springboot.entidades.Autor;
import newLibreria.springboot.entidades.Editorial;
import newLibreria.springboot.entidades.Libro;
import newLibreria.springboot.repositorios.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServicio {
    
    @Autowired
    private LibroRepositorio libroRepositorio;
    
    @Autowired
    private AutorServicio autorServicio;
    
    @Autowired
    private EditorialServicio editorialServicio;
    
    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer anio, Integer ejemplares, String autorId, String editorialId) {
        
        Libro libro = new Libro();
        
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(0);
        libro.setEjemplaresRestantes(ejemplares);
        libro.setAutor(autorServicio.buscarPorId(autorId));
        libro.setEditorial(editorialServicio.buscarPorId(editorialId));
        
        libroRepositorio.save(libro);
        
    }
    
    
     @Transactional
    public void modificarLibro(Long isbn, String titulo, Integer anio, Integer ejemplares,Integer ejemplaresPrestados,Integer ejemplaresRestantes, String autorId, String editorialId) {
        Libro libro = libroRepositorio.findById(isbn).get();
        
        
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(ejemplaresPrestados);
        libro.setEjemplaresRestantes(ejemplaresRestantes);
        libro.setAutor(autorServicio.buscarPorId(autorId));
        libro.setEditorial(editorialServicio.buscarPorId(editorialId));
        
        
        libroRepositorio.save(libro);
    }
    
     @Transactional(readOnly = true)
    public List<Libro> buscarTodos() {
        return libroRepositorio.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Libro> mostrarLibrosHabilitados(){
        return libroRepositorio.mostrarLibrosHabilitados();
    }
    
    
     @Transactional(readOnly = true)
    public Libro buscarPorId(Long isbn) {
        Optional<Libro> libroOptional = libroRepositorio.findById(isbn);
        return libroOptional.orElse(null);
    }
    
    
   @Transactional
    public void habilitar(Long isbn) {
        libroRepositorio.modificarAlta(isbn, true);
    }
    
    @Transactional
    public void deshabilitar(Long isbn) {
        libroRepositorio.modificarAlta(isbn, false);
    }
    
}
