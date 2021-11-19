package newLibreria.springboot.repositorios;

import java.util.List;
import newLibreria.springboot.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String>{
    
  /*   @Modifying
    @Query("UPDATE Editorial e SET e.nombre = :nombre WHERE e.id = :id")
    void modificar(@Param("id") String id, @Param("nombre") String nombre);*/
    
    @Modifying
    @Query("UPDATE Editorial e SET e.alta = :alta WHERE e.id = :id")
    void modificarAlta(@Param("id") String id, @Param("alta") Boolean alta);
    
    @Query("SELECT e FROM Editorial e WHERE e.alta=true")
    List<Editorial> mostrarEditorialesHabilitadas();
    
    @Query("SELECT e FROM Editorial e WHERE e.alta=false")
    List<Editorial> mostrarEditorialesDeshabilitadas();
    
}
