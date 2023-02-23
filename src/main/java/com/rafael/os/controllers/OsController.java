package com.rafael.os.controllers;

import com.rafael.os.dtos.OSDTO;
import com.rafael.os.services.OsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping (value = "/os")
public class OsController {


    @Autowired
    private OsService service;
    @GetMapping(value = "/{id}")
    public ResponseEntity<OSDTO> findById(@PathVariable Integer id) {
      OSDTO objDTO = new OSDTO(service.findById(id));
        return ResponseEntity.ok().body(objDTO);
    }

    @GetMapping
    public ResponseEntity<List<OSDTO>> findAll() {
        List<OSDTO> list = service.findall().stream().
                map(obj -> new OSDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<OSDTO> create(@Valid @RequestBody OSDTO obj){
          obj = new OSDTO(service.create(obj));
          URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
          return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<OSDTO> update(@RequestBody OSDTO obj){
        obj = new OSDTO(service.update(obj));
        return ResponseEntity.ok().body(obj);

    }
}
