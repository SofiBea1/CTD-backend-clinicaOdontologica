package com.dh.clinica.service.imp;

import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.persistence.repository.IOdontologoRepository;
import com.dh.clinica.service.IService;
import com.dh.clinica.persistence.entities.Odontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OdontologoService implements IService<OdontologoDto> {

    @Autowired
    IOdontologoRepository repository;

    @Override
    public OdontologoDto buscar(Integer id) throws BadRequestException, ResourceNotFoundException {
        if(id == null || id<1) {
            throw new BadRequestException("No se puede buscar un odontologo con ID null o negativo");
        } else if(repository.getById(id) == null){
            throw new ResourceNotFoundException("No se encontró el odontólogo con ese ID");
        }else{
            return  new OdontologoDto(repository.getById(id));
        }
    }

    @Override
    public OdontologoDto crear(OdontologoDto odontologoDto) throws BadRequestException {
        if(odontologoDto == null ){
            throw new BadRequestException("No se puede crear el odontólogo sin datos");
        } else {
            Odontologo o = odontologoDto.toEntity();
            repository.save(o);
            odontologoDto.setId(o.getId());
            return odontologoDto;
        }
    }

    @Override
    public OdontologoDto actualizar(OdontologoDto odontologoDto) throws BadRequestException{
        if(odontologoDto ==  null){
            throw new BadRequestException("No se puede actualizar el odontólogo sin datos");
        } else {
            repository.save(odontologoDto.toEntity());
            return odontologoDto;
        }
    }

    @Override
    public void eliminar(Integer id) throws BadRequestException, ResourceNotFoundException{
        if (id == null || id<1){
            throw new BadRequestException("No se puede eliminar el odontólogo con id null o negativo");
        } else if (repository.getById(id) == null){
            throw new ResourceNotFoundException("No se encontró el odontólogo con ese ID");
        } else {
            repository.delete(repository.getById(id));
        }
    }

    @Override
    public List<OdontologoDto> consultarTodos() {
        List<OdontologoDto> odontologos = new ArrayList<>();

        for(Odontologo o: repository.findAll()){
            odontologos.add(new OdontologoDto(o));
        }
        return odontologos;
    }
}
