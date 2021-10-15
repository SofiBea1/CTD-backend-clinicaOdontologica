package com.dh.clinica.controller;

import com.dh.clinica.dto.TurnoDto;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.service.imp.TurnoService;
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
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    TurnoService turnoService;

    private final Logger logger = Logger.getLogger(PacienteController.class);

    @GetMapping("/todos")
    public ResponseEntity<List<TurnoDto>> consultarTodos() {
        logger.debug("Iniciando el método 'consultarTodos'");
        return ResponseEntity.ok(turnoService.consultarTodos());
    }

    @ApiOperation(value = "Buscar turno por ID")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "Henry si estás viendo esto, todo salió bien!!"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Integer id) throws BadRequestException, ResourceNotFoundException {

        logger.debug("Iniciando el método 'buscarPorId");

            logger.debug("Se encontro el turno");
            return ResponseEntity.ok(turnoService.buscar(id));
    }

    @ApiOperation(value = "Ver turnos de la próxima semana")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @GetMapping("/proximaSemana")
    public ResponseEntity<List<TurnoDto>> consultarProximosTurnos() {
        logger.debug("Iniciando el método 'consultarProximosTurnos'");
        return ResponseEntity.ok(turnoService.consultarProximosTurnos());
    }

    @ApiOperation(value = "Crear un nuevo turno")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PostMapping("/nuevo")
    public ResponseEntity<TurnoDto> crearNuevoTurno(@RequestBody TurnoDto turno) throws BadRequestException, ResourceNotFoundException {
        logger.debug("Iniciando el método 'crearNuevo (turno)'");
        return ResponseEntity.ok(turnoService.crear(turno));
    }

    @ApiOperation(value = "Modificar los datos de un turno")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PutMapping("/modificar")
    public ResponseEntity<?> actualizarTurno(@RequestBody TurnoDto turno) throws BadRequestException, ResourceNotFoundException {

        logger.debug("Iniciando el método 'actualizar(turno)'");

        if(turno.getId() != null) {
            logger.debug("Se pudo actualizar el turno");
            return ResponseEntity.ok(turnoService.actualizar(turno));
        }else{
            logger.debug("No se pudo actualizar el turno");
            return ResponseEntity.badRequest().body("El turno porque no es valido");
        }

    }


    @ApiOperation(value = "Eliminar turno")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPorId(@PathVariable("id") Integer id) throws BadRequestException, ResourceNotFoundException {

        logger.debug("Iniciando el método 'eliminarPorId'");

        ResponseEntity<String> response;
        if(turnoService.buscar(id) != null){
         turnoService.eliminar(id);
            response = ResponseEntity.ok("Se eliminó el turno con id "+id);
            logger.debug("Se eliminó el turno con id "+id);
        }else{
            response= ResponseEntity.badRequest().body("No se encontro el turno");
            logger.debug("No se encontro el turno con id "+id);
        }
        return response;
    }
}
