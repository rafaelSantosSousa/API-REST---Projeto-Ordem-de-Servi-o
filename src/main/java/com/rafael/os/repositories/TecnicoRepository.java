package com.rafael.os.repositories;

import com.rafael.os.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoRepository extends JpaRepository <Tecnico, Integer> {


}
