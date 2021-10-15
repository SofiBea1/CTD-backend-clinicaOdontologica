package com.dh.clinica.service.imp;


import com.dh.clinica.dto.TurnoDto;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.persistence.entities.Turno;
import com.dh.clinica.persistence.repository.ITurnoRepository;
import com.dh.clinica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class TurnoService implements IService<TurnoDto> {


    @Autowired
    ITurnoRepository repository;

    @Autowired
    OdontologoService serviceOdontologo;

    @Autowired
    PacienteService servicePaciente;

    @Override
    public TurnoDto buscar(Integer id) throws BadRequestException, ResourceNotFoundException{
        if (id == null || id < 1) {
            throw new BadRequestException("No se puede buscar un turno con id null o negativo");
        } else if (repository.getById(id) == null) {
            throw new ResourceNotFoundException("No se encontró el turno con ese ID");
        } else {
            return new TurnoDto(repository.getById(id));
        }
    }


    @Override
    public TurnoDto actualizar(TurnoDto turnoDto) throws BadRequestException, ResourceNotFoundException {
        boolean checkQueExisteElTurno = (buscar(turnoDto.getId())!= null);
        boolean checkDisponibilidad = verificarDisponibilidad(turnoDto);
        if(checkQueExisteElTurno && checkDisponibilidad) {
            turnoDto.setPaciente(servicePaciente.buscar(turnoDto.getPaciente().getId()));
            turnoDto.setOdontologo(serviceOdontologo.buscar(turnoDto.getOdontologo().getId()));
            return new TurnoDto(repository.save(turnoDto.toEntity()));
        }else{
            throw new BadRequestException("No se puede actualizar el turno");
        }
    }

    @Override
    public void eliminar(Integer id) throws BadRequestException, ResourceNotFoundException {
        if (id == null || id < 1) {
            throw new BadRequestException("No se puede eliminar el turno con id null o negativo");
        } else if (repository.getById(id) == null) {
            throw new ResourceNotFoundException("No se encontró el turno con ese ID");
        } else {
            repository.delete(repository.getById(id));
        }
    }

    @Override
    public List<TurnoDto> consultarTodos() {
        List<TurnoDto> turnos = new ArrayList<>();

        for(Turno t : repository.findAll()){
            turnos.add(new TurnoDto(t));
        }

        return turnos;
    }

    public List<TurnoDto> consultarProximosTurnos() {
        List<TurnoDto> turnosProximaSemana = new ArrayList<>();
        for(Turno t : repository.findAll()){
            if(t.getFechaYHora().isBefore(LocalDateTime.now().plusWeeks(1)) && t.getFechaYHora().isAfter(LocalDateTime.now())) {
                turnosProximaSemana.add(new TurnoDto(t));
            }
        }

        return turnosProximaSemana;
    }

    @Override
    public TurnoDto crear(TurnoDto turnoDto) throws BadRequestException, ResourceNotFoundException {

        Integer pacienteId = turnoDto.getPaciente().getId();
        Integer odontologoId = turnoDto.getOdontologo().getId();

        if(this.verficarTurno(pacienteId,odontologoId,turnoDto.getFechaYHora()) && this.verificarDisponibilidad(turnoDto)){
            turnoDto.setPaciente(servicePaciente.buscar(pacienteId));
            turnoDto.setOdontologo(serviceOdontologo.buscar(odontologoId));
            Turno turnoGuardado = repository.save(turnoDto.toEntity());
            turnoDto.setId(turnoGuardado.getId());

            return turnoDto;
        }else{
            //Exception ("El odontólogo y/o el paciente no existen")
           throw new BadRequestException("No se puede crear el turno si el odontologo o el paciente son nulos");
        }
    }

    private boolean verficarTurno(Integer pacienteId, Integer odontologoId, LocalDateTime fechaYHora) throws BadRequestException, ResourceNotFoundException {

        return (servicePaciente.buscar(pacienteId) != null && serviceOdontologo.buscar(odontologoId) !=null && fechaYHora != null);

    }

    private boolean verificarDisponibilidad(TurnoDto turnoNuevo){
        boolean check = true;
        for (TurnoDto turno: this.consultarTodos()) {
             if(turno.equals(turnoNuevo)){
                return check = false;
             }
        }
        return check;
    }


}
