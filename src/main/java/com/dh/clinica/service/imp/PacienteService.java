package com.dh.clinica.service.imp;

import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.persistence.repository.IPacienteRepository;
import com.dh.clinica.service.IService;
import com.dh.clinica.persistence.entities.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService implements IService<PacienteDto> {

    @Autowired
    IPacienteRepository repository;

    @Override
    public PacienteDto crear(PacienteDto p) throws BadRequestException {
        if (p == null) {
            throw new BadRequestException("No se puede crear el paciente sin datos");
        } else {
            p.setFechaIngreso(LocalDate.now());
            Paciente pacienteGuardado = repository.save(p.toEntity());
            p.setId(pacienteGuardado.getId());
            p.getDomicilio().setId(pacienteGuardado.getDomicilio().getId());
            return p;
        }
    }

    @Override
    public PacienteDto buscar(Integer id) throws BadRequestException, ResourceNotFoundException {
        if (id == null || id < 1) {
            throw new BadRequestException("No se puede buscar un paciente con id null o negativo");
        } else if (repository.getById(id) == null) {
            throw new ResourceNotFoundException("No se encontró el paciente con ese ID");
        } else {
            return new PacienteDto(repository.getById(id));
        }
    }


    @Override
    public PacienteDto actualizar(PacienteDto p) throws BadRequestException {
        if (p == null) {
            throw new BadRequestException("No se puede actualizar el paciente sin datos");
        } else {
            Paciente pacienteEnBD = repository.getById(p.getId());
            p.getDomicilio().setId(pacienteEnBD.getDomicilio().getId());
            repository.save(p.toEntity());
            return p;
        }
    }

    @Override
    public void eliminar(Integer id) throws BadRequestException, ResourceNotFoundException {
        if (id == null || id < 1) {
            throw new BadRequestException("No se puede eliminar el paciente con id null o negativo");
        } else if (repository.getById(id) == null) {
            throw new ResourceNotFoundException("No se encontró el paciente con ese ID");
        } else {
            repository.delete(repository.getById(id));
        }
    }

    @Override
    public List<PacienteDto> consultarTodos() {
        List<PacienteDto> pacientes = new ArrayList<>();

        for (Paciente p : repository.findAll()) {
            pacientes.add(new PacienteDto(p));
        }

        return pacientes;
    }
}