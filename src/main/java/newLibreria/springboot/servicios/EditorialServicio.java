package newLibreria.springboot.servicios;

import java.util.List;
import java.util.Optional;
import newLibreria.springboot.entidades.Editorial;
import newLibreria.springboot.repositorios.EditorialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialServicio {
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    @Transactional
    public void crearEditorial(String nombre){
        Editorial editorial = new Editorial();
        
        editorial.setNombre(nombre);
        
        editorialRepositorio.save(editorial);
    }
    
    @Transactional(readOnly = true)
    public List<Editorial> buscarTodos() {
        return editorialRepositorio.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Editorial> mostrarEditorialesHabilitadas(){
        return editorialRepositorio.mostrarEditorialesHabilitadas();
    }
    
    @Transactional(readOnly = true)
    public List<Editorial> mostrarEditorialesDeshabilitadas(){
        return editorialRepositorio.mostrarEditorialesDeshabilitadas();
    }
    
    @Transactional(readOnly = true)
    public Editorial buscarPorId(String id) {
        Optional<Editorial> editorialOptional = editorialRepositorio.findById(id);
        return editorialOptional.orElse(null);
    }
    
    
    @Transactional
    public void modificarEditorial(String id, String nombre) {
        Editorial editorial = editorialRepositorio.findById(id).get();
        
        editorial.setNombre(nombre);
        
        editorialRepositorio.save(editorial);
    }
    
     @Transactional
    public void habilitar(String id) {
        editorialRepositorio.modificarAlta(id, true);
    }
    
    @Transactional
    public void deshabilitar(String id) {
        editorialRepositorio.modificarAlta(id, false);
    }
    
}
