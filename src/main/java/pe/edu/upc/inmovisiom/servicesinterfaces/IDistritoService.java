package pe.edu.upc.inmovisiom.servicesinterfaces;

import pe.edu.upc.inmovisiom.entities.Distrito;

import java.util.List;

public interface IDistritoService {
    public List<Distrito> list();
    public void insert(Distrito distrito);
    public Distrito listId(int id);
    public void delete(int id);
    public void update(Distrito distrito);
    public List<Distrito> buscarService(String nombre);
}
