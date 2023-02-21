package com.rafael.os.controllers;


import com.rafael.os.domain.Tecnico;
import com.rafael.os.dtos.TecnicoDTO;
import com.rafael.os.services.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {
    @Autowired
    private TecnicoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        TecnicoDTO objDTO = new TecnicoDTO(service.findById(id));
        return ResponseEntity.ok().body(objDTO);
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<TecnicoDTO> listDTO = new ArrayList<>();
        for (Tecnico obj : service.findAll()) {
            TecnicoDTO tecnicoDTO = new TecnicoDTO(obj);
            listDTO.add(tecnicoDTO);
        }
        return ResponseEntity.ok().body(listDTO);
    }
    @PostMapping
    public ResponseEntity<TecnicoDTO> create (@Valid @RequestBody TecnicoDTO objDTO) {
       Tecnico newObj = service.create(objDTO);
       URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
       return ResponseEntity.created(uri).build();
    }
}

