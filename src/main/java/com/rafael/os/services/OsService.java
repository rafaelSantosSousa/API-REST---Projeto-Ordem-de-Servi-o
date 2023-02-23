package com.rafael.os.services;

import com.rafael.os.domain.Cliente;
import com.rafael.os.domain.OS;
import com.rafael.os.domain.Tecnico;
import com.rafael.os.domain.enuns.Prioridade;
import com.rafael.os.domain.enuns.Status;
import com.rafael.os.dtos.OSDTO;
import com.rafael.os.repositories.OSRepository;
import com.rafael.os.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OsService {

    @Autowired
    private OSRepository repository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    public OS findById(Integer id) {
        Optional<OS> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Ordem de serviço não foi encontrada! Id " + id + " Tipo: " + OS.class.getName()));
    }

    public List<OS> findall(){
        return repository.findAll();
    }

    public OS create(OSDTO obj) {
         return fromDTO(obj);
    }

    private OS fromDTO(OSDTO obj) {
       OS newObj = new OS ();
       newObj.setId(obj.getId());
       newObj.setObservacoes(obj.getObservacoes());
       newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
       newObj.setStatus(Status.toEnum(obj.getStatus()));

        Tecnico tec = tecnicoService.findById(obj.getTecnico());
        Cliente cli = clienteService.findById(obj.getCliente());

        newObj.setTecnico(tec);
        newObj.setCliente(cli);

        if(newObj.getStatus().getCod().equals(2)) {
            newObj.setDataFechamento(LocalDateTime.now());
        }

        return repository.save(newObj);
    }

    public OS update(@Valid OSDTO obj) {
        findById(obj.getId());
        return fromDTO(obj);
    }
}
