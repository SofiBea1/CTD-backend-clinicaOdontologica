package com.dh.clinica.service;

import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.service.imp.OdontologoService;
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

public class OdontologoServiceTest {


    @Autowired
    private OdontologoService odontologoService;
    private OdontologoDto odontologo;


    @BeforeEach
    public void datosIniciales() throws Exception {
        odontologoService.crear(new OdontologoDto(1, "Fulano", "Mengano", "33399"));
    }


        @Test
        public void test01_guardarOdontologo() throws Exception {
            OdontologoDto o = new OdontologoDto();
            o.setId(2);
            o.setNombre("Maria");
            o.setApellido("Guzman");
            o.setMatricula("987654");
            odontologo = odontologoService.crear(o);
            Assertions.assertTrue(odontologo.getId() != null);
        }

        @Test
        public void test02_listarOdontologos() throws Exception{
            datosIniciales();
            Integer respuestaEsperada = 1;
            Integer respuestaObtenida = odontologoService.consultarTodos().size();
            Assertions.assertEquals(respuestaEsperada, respuestaObtenida);
        }


        @Test
        public void test03_borrarOdontologoPorId() throws Exception {
            datosIniciales();
            odontologoService.eliminar(1);
            Integer respuestaEsperada = 0;
            Integer respuestaObtenida = odontologoService.consultarTodos().size();
            Assertions.assertEquals(respuestaEsperada, respuestaObtenida);

        }
    }