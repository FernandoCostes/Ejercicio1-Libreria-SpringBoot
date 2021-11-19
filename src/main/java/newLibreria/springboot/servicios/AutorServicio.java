
package newLibreria.springboot.servicios;

import java.util.List;
import java.util.Optional;
import newLibreria.springboot.entidades.Autor;
import newLibreria.springboot.repositorios.AutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AutorServicio {
    
    @Autowired
    private AutorRepositorio autorRepositorio;
    
    @Transactional
    public void crearAutor(String nombre){
        Autor autor = new Autor();
        
        autor.setNombre(nombre);
        
        autorRepositorio.save(autor);
    }
    
    @Transactional
    public void modificarAutor(String id, String nombre) {
        Autor autor = autorRepositorio.findById(id).get();
        
        autor.setNombre(nombre);
        
        autorRepositorio.save(autor);
    }
    
     @Transactional(readOnly = true)
    public List<Autor> buscarTodos() {
        return autorRepositorio.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Autor> mostrarAutoresHabilitados(){
        return autorRepositorio.mostrarAutoresHabilitados();
    }
    
    @Transactional(readOnly = true)
    public List<Autor> mostrarAutoresDeshabilitados(){
        return autorRepositorio.mostrarAutoresDeshabilitados();
    }
    
    
     @Transactional(readOnly = true)
    public Autor buscarPorId(String id) {
        Optional<Autor> autorOptional = autorRepositorio.findById(id);
        return autorOptional.orElse(null);
    }
    
    
   @Transactional
    public void habilitar(String id) {
        autorRepositorio.modificarAlta(id, true);
    }
    
    @Transactional
    public void deshabilitar(String id) {
        autorRepositorio.modificarAlta(id, false);
    }


    
}
