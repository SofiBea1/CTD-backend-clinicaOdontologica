package com.dh.clinica.persistence.repository;


import com.dh.clinica.persistence.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IPacienteRepository extends JpaRepository<Paciente, Integer > {
}
