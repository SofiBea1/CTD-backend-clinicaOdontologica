package com.dh.clinica.controller;


import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.service.imp.PacienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;
    private final Logger logger = Logger.getLogger(PacienteController.class);

    @ApiOperation(value = "Buscar paciente por ID")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "Henry si estás viendo esto, todo salió bien!!"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Integer id) throws ResourceNotFoundException, BadRequestException {

        logger.debug("Iniciando el método 'buscarPorId");

        if(pacienteService.buscar(id) != null){
            logger.debug("Se encontro el paciente");
            return ResponseEntity.ok(pacienteService.buscar(id));
        }else{
            logger.debug("No se encontro el paciente");
            return null;
        }

    }

    @GetMapping("/todos")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<PacienteDto>> consultarTodos() {
        logger.debug("Iniciando el método 'consultarTodos'");
        return ResponseEntity.ok(pacienteService.consultarTodos());
    }

    @ApiOperation(value = "Crear un nuevo paciente")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PostMapping("/nuevo")
    public ResponseEntity<PacienteDto> crearNuevoPaciente(@RequestBody PacienteDto paciente) throws BadRequestException{
        logger.debug("Iniciando el método 'crearNuevo (paciente)'");
        return ResponseEntity.ok(pacienteService.crear(paciente));
    }

    @ApiOperation(value = "Modificar los datos de un paciente")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PutMapping("/modificar")
    public ResponseEntity<PacienteDto> actualizarPaciente(@RequestBody PacienteDto paciente) throws BadRequestException {
        logger.debug("Iniciando el método 'actualizar(odontologo)'");
            logger.debug("Se pudo actualizar el paciente");
            return ResponseEntity.ok(pacienteService.actualizar(paciente));
    }


    @ApiOperation(value = "Eliminar paciente")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") Integer id) throws BadRequestException, ResourceNotFoundException {
        logger.debug("Iniciando el método 'eliminarPorId'");
          pacienteService.eliminar(id);
          return ResponseEntity.ok("Se eliminó el paciente con id "+id);
    }

}
