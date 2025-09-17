package pe.edu.upc.inmovisiom.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmovisiom.dtos.PropiedadDTO;
import pe.edu.upc.inmovisiom.entities.Propiedad;
import pe.edu.upc.inmovisiom.servicesinterfaces.IPropiedadService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/propiedad")
public class PropiedadController {

    @Autowired
    private IPropiedadService pS;

    @GetMapping
    public List<PropiedadDTO> listar() {
        return pS.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, PropiedadDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody PropiedadDTO dto) {
        ModelMapper m = new ModelMapper();
        Propiedad p = m.map(dto, Propiedad.class);
        pS.insert(p);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Propiedad p = pS.listId(id);
        if (p == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        PropiedadDTO dto = m.map(p, PropiedadDTO.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Propiedad p = pS.listId(id);
        if (p == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        pS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody PropiedadDTO dto) {
        ModelMapper m = new ModelMapper();
        Propiedad p = m.map(dto, Propiedad.class);

        Propiedad existente = pS.listId(p.getIdPropiedad());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + p.getIdPropiedad());
        }

        pS.update(p);
        return ResponseEntity.ok("Registro con ID " + p.getIdPropiedad() + " modificado correctamente.");
    }
}
