package newLibreria.springboot.repositorios;

import java.util.List;
import newLibreria.springboot.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String> {
    
    /*@Modifying
    @Query("UPDATE Autor a SET a.nombre = :nombre WHERE a.id = :id")
    void modificar(@Param("id") String id, @Param("nombre") String nombre);*/
    
    @Modifying
    @Query("UPDATE Autor a SET a.alta = :alta WHERE a.id = :id")
    void modificarAlta(@Param("id") String id, @Param("alta") Boolean alta);
    
    @Query("SELECT a FROM Autor a WHERE a.alta=true")
    List<Autor> mostrarAutoresHabilitados();
    
    @Query("SELECT a FROM Autor a WHERE a.alta=false")
    List<Autor> mostrarAutoresDeshabilitados();
    
    // List<Autor> findByAltaTrue() traeme los autores a una lista pero donde el campo alta sea = true

    //List<Autor> findByAltaTrueOrderByNombreAsc();
    
    //Autor findByNombreIgnoreCase(String nombre);


}
