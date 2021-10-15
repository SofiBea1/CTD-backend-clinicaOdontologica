package com.dh.clinica.dto;

import com.dh.clinica.persistence.entities.Turno;

import java.time.LocalDateTime;

public class TurnoDto {

    private Integer id;
    private LocalDateTime fechaYHora;
    private OdontologoDto odontologo;
    private PacienteDto paciente;

    public TurnoDto() {
    }

    public TurnoDto(Integer id, LocalDateTime fechaYHora, OdontologoDto odontologo, PacienteDto paciente) {
        this.id = id;
        this.fechaYHora = fechaYHora;
        this.odontologo = odontologo;
        this.paciente = paciente;
    }

    public TurnoDto(Turno t) {
        this.id = t.getId();
        this.fechaYHora = t.getFechaYHora();
        this.odontologo = new OdontologoDto(t.getOdontologo());
        this.paciente = new PacienteDto(t.getPaciente());
    }

    public Turno toEntity() {
         Turno entity = new Turno();

        entity.setId(id);
        entity.setFechaYHora(fechaYHora);
        entity.setOdontologo(odontologo.toEntity());
        entity.setPaciente(paciente.toEntity());

        return entity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public OdontologoDto getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(OdontologoDto odontologo) {
        this.odontologo = odontologo;
    }

    public PacienteDto getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDto paciente) {
        this.paciente = paciente;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TurnoDto turnoDto = (TurnoDto) o;
        return (fechaYHora.equals(turnoDto.getFechaYHora()) && odontologo.getId() == turnoDto.getOdontologo().getId());
    }




}
