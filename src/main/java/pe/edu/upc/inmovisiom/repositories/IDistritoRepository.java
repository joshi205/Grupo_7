package pe.edu.upc.inmovisiom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inmovisiom.entities.Distrito;

import java.util.List;

@Repository
public interface IDistritoRepository extends JpaRepository<Distrito,Integer> {
    @Query("select dis from Distrito dis where dis.nombreDistrito like %:tipo%")
    public List<Distrito> buscarR(@Param("nombre") String tipo);
}
