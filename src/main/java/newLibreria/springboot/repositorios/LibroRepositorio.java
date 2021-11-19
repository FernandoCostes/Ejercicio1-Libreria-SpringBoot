package newLibreria.springboot.repositorios;

import java.util.List;
import newLibreria.springboot.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    
    @Modifying
    @Query("UPDATE Libro l SET l.alta = :alta WHERE l.isbn = :isbn")
    void modificarAlta(@Param("isbn") Long isbn, @Param("alta") Boolean alta);
    
    @Query("SELECT l FROM Libro l WHERE l.alta=true")
    List<Libro> mostrarLibrosHabilitados();
    
}
