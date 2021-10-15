package com.dh.clinica.persistence.repository;

import com.dh.clinica.persistence.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOdontologoRepository extends JpaRepository<Odontologo, Integer> {
}
