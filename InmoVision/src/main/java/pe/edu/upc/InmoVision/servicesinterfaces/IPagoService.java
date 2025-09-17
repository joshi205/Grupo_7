package pe.edu.upc.inmovisiom.servicesinterfaces;

import pe.edu.upc.inmovisiom.entities.Pago;
import java.util.List;

public interface IPagoService {
    void insert(Pago pago);
    List<Pago> list();
    Pago listId(int id);
    void delete(int id);
    void update(Pago pago);
}

