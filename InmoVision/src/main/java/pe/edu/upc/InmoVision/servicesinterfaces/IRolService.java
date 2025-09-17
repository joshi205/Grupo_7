package pe.edu.upc.inmo.servicesinterfaces;

import pe.edu.upc.inmo.entities.Rol;

import java.util.List;

public interface IRolService {
    public List<Rol> list();
    public void insert(Rol rol);
    public void delete(Rol rol);
}
