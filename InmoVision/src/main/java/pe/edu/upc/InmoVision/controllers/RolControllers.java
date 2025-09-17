package pe.edu.upc.inmo.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.inmo.dtos.RolDTO;
import pe.edu.upc.inmo.entities.Rol;
import pe.edu.upc.inmo.servicesinterfaces.IRolService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rol")
public class RolControllers {
    @Autowired
    private IRolService dR;
    @GetMapping
    public List<RolDTO> listar(){
        return dR.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y,RolDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping
    public void insertar(@RequestBody RolDTO dto){
        ModelMapper m = new ModelMapper();
        Rol d=m.map(dto,Rol.class);
        dR.insert(d);
    }
}
