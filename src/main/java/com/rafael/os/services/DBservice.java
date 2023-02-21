package com.rafael.os.services;


import com.rafael.os.repositories.ClienteRepository;
import com.rafael.os.repositories.OSRepository;
import com.rafael.os.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DBservice {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private OSRepository osRepository;

}

