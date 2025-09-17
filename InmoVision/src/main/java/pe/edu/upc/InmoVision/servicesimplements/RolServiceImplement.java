package pe.edu.upc.inmo.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.inmo.entities.Rol;
import pe.edu.upc.inmo.repositories.IRolRepository;
import pe.edu.upc.inmo.servicesinterfaces.IRolService;

import java.util.List;

@Service
public class RolServiceImplement implements IRolService {
    @Autowired
    private IRolRepository dR;

    @Override
    public List<Rol> list() {return dR.findAll();}

    @Override
    public void insert(Rol rol) {
        dR.save(rol);
    }

    @Override
    public void delete(Rol rol) {

    }

}
