package com.rafael.os.services;

import com.rafael.os.domain.Tecnico;
import com.rafael.os.dtos.TecnicoDTO;
import com.rafael.os.repositories.TecnicoRepository;
import com.rafael.os.services.exceptions.DataIntegratyViolationException;
import com.rafael.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;
     public Tecnico findById(Integer id){
     Optional<Tecnico> obj = repository.findById(id);
          return obj.orElseThrow(() -> new ObjectNotFoundException(
                  "Objeto não encontrado! Id " + id + " Tipo: " + Tecnico.class.getName()));
 }
     public List<Tecnico> findAll() {
         return repository.findAll();
    }

     public Tecnico create (TecnicoDTO objDTO) {
         if(findByCPF(objDTO)!=null){
             throw new DataIntegratyViolationException("CPF já cadastrado na base de dados.");
         }
           return null;
           }

     private Tecnico findByCPF(TecnicoDTO objDTO){
         Tecnico obj = repository.findByCPF(objDTO.getCpf());
         if (obj != null) {
             return obj;}
                 return null;
     }

}

