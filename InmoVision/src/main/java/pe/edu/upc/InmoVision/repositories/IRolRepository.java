package pe.edu.upc.inmo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.inmo.entities.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol,Integer> {
}
