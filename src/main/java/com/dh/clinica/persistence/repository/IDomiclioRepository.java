package com.dh.clinica.persistence.repository;

import com.dh.clinica.persistence.entities.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDomiclioRepository extends JpaRepository<Domicilio, Integer> {
}
