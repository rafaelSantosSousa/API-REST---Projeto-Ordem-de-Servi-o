package com.rafael.os.services;

import com.rafael.os.domain.Cliente;
import com.rafael.os.domain.Pessoa;
import com.rafael.os.dtos.ClienteDTO;
import com.rafael.os.repositories.ClienteRepository;
import com.rafael.os.repositories.PessoaRepository;
import com.rafael.os.services.exceptions.DataIntegratyViolationException;
import com.rafael.os.services.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;
    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + " Tipo: " + Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(ClienteDTO objDTO) {
        if (findByCPF(objDTO) != null) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados.");
        }
        return null;
    }

    public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
        Cliente oldObj = findById(id);
        if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id){
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados.");
        }
        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());
         return repository.save(oldObj);
    }
     //deleta tecnico por id
    public void delete(Integer id) {
        Cliente obj = findById(id);
        if(obj.getList().size() > 0 ){
            throw new DataIntegratyViolationException("Técnico possui Ordens de Serviço, não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    //busca tecnico pelo cpf
    private Pessoa findByCPF(ClienteDTO objDTO){
        Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
        if (obj != null) {
            return obj;}
        return null;
    }


}

