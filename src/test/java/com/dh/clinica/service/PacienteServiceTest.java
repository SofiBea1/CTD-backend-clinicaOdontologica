package com.dh.clinica.service;
import com.dh.clinica.dto.DomicilioDto;
import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.service.imp.PacienteService;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class PacienteServiceTest {


    @Autowired
    private PacienteService pacienteService;
    private DomicilioDto domicilioDto;
    private PacienteDto pacienteDto;


    @BeforeEach
    public void datosIniciales() throws Exception {
        DomicilioDto domicilioPte = new DomicilioDto("Calle 1", "1234", "La Plata", "Buenos Aires");
        pacienteService.crear(new PacienteDto(1, "dolores", "de muelas", "33333339", domicilioPte));
    }


    @Test
    public void test01_guardarPaciente() throws Exception {
        PacienteDto o = new PacienteDto();
        o.setId(2);
        o.setNombre("Fulanita");
        o.setApellido("Mengana");
        o.setDomicilio(new DomicilioDto("Calle 1", "1234", "La Plata", "Buenos Aires"));
        pacienteDto = pacienteService.crear(o);
        Assertions.assertTrue(pacienteDto.getId() != null);
    }

    @Test
    public void test02_listarPacientes() throws Exception{
        datosIniciales();
        Integer respuestaEsperada = 1;
        Integer respuestaObtenida = pacienteService.consultarTodos().size();
        Assertions.assertEquals(respuestaEsperada, respuestaObtenida);
    }


    @Test
    public void test03_borrarPacientePorId() throws Exception {
        datosIniciales();
        pacienteService.eliminar(1);
        Integer respuestaEsperada = 0;
        Integer respuestaObtenida = pacienteService.consultarTodos().size();
        Assertions.assertEquals(respuestaEsperada, respuestaObtenida);

    }

}
