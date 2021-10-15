package com.dh.clinica.service;

import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IService<T> {
    T buscar(Integer id) throws ResourceNotFoundException, BadRequestException;
    T crear(T t) throws BadRequestException, ResourceNotFoundException;
    T actualizar(T t) throws BadRequestException, ResourceNotFoundException;
    void eliminar(Integer id) throws BadRequestException, ResourceNotFoundException;
    List<T> consultarTodos();

}
