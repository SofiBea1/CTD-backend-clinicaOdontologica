package com.dh.clinica.controller;

import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.service.imp.OdontologoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    OdontologoService odontologoService;
    private final Logger logger = Logger.getLogger(OdontologoController.class);

    @ApiOperation(value = "Buscar odontólogo por ID")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "Henry si estás viendo esto, todo salió bien!!"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Integer id) throws ResourceNotFoundException, BadRequestException {
        logger.debug("Iniciando el método 'buscarPorId");
        if(odontologoService.buscar(id) != null){
            return ResponseEntity.ok(odontologoService.buscar(id));
        }else{
           return null;
        }
    }

    @ApiOperation(value = "Crear un nuevo odontólogo")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PostMapping("/nuevo")
    public ResponseEntity<OdontologoDto> crearNuevo(@RequestBody OdontologoDto odontologo) throws BadRequestException {
        logger.debug("Iniciando el método 'crearNuevo (odontologo)'");
        return ResponseEntity.ok(odontologoService.crear(odontologo));
    }

    @ApiOperation(value = "Modificar los datos de un odontólogo")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PutMapping("/modificar")
    public ResponseEntity<OdontologoDto> actualizar(@RequestBody OdontologoDto odontologo) throws BadRequestException{
        logger.debug("Iniciando el método 'actualizar(odontologo)'");
            return ResponseEntity.ok(odontologoService.actualizar(odontologo));
    }

    @ApiOperation(value = "Eliminar odontólogo")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable("id") Integer id) throws BadRequestException, ResourceNotFoundException{
        logger.debug("Iniciando el método 'eliminarPorId'");
            odontologoService.eliminar(id);
            return ResponseEntity.ok("Se eliminó el odontólogo con id "+id);
    }

    @ApiOperation(value = "Listar todos los odontólogos")
    @GetMapping("/todos")
    public ResponseEntity<List<OdontologoDto>> consultarTodos(){
        logger.debug("Iniciando el método 'consultarTodos'");
        return ResponseEntity.ok(odontologoService.consultarTodos());
    }
}
