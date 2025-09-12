package pe.edu.upc.inmovisiom.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmovisiom.dtos.DistritoDTO;
import pe.edu.upc.inmovisiom.entities.Distrito;
import pe.edu.upc.inmovisiom.servicesinterfaces.IDistritoService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/distritos")
public class DistritoController {
    @Autowired
    private IDistritoService dS;
    @GetMapping
    public List<DistritoDTO> listar(){
        return dS.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y,DistritoDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping
    public void insertar(@RequestBody DistritoDTO dto){
        ModelMapper m = new ModelMapper();
        Distrito d=m.map(dto,Distrito.class);
        dS.insert(d);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Distrito dis = dS.listId(id);
        if (dis == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        DistritoDTO dto = m.map(dis, DistritoDTO.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Distrito d = dS.listId(id);
        if (d == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        dS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody DistritoDTO dto) {
        ModelMapper m = new ModelMapper();
        Distrito dis = m.map(dto, Distrito.class);

        Distrito existente = dS.listId(dis.getIdDistrito());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + dis.getIdDistrito());
        }

        dS.update(dis);
        return ResponseEntity.ok("Registro con ID " + dis.getIdDistrito() + " modificado correctamente.");
    }
}


