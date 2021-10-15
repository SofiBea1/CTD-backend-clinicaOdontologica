package com.dh.clinica.controller;

import com.dh.clinica.dto.OdontologoDto;
import com.util.Jsons;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)

class OdontologoControllerTest {

    @Autowired
    private MockMvc mvc;
    private final OdontologoDto od = new OdontologoDto("Fulano", "Mengano", "33339");

    @Test
    void test01_crearNuevoOdontologo() throws Exception{

        OdontologoDto resp = new OdontologoDto(1,"Fulano", "Mengano", "33339");
        MvcResult response = this.mvc.perform(MockMvcRequestBuilders.post("/odontologos/nuevo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Jsons.asJsonString(od)))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        Assert.assertTrue(response.getResponse().getContentAsString().equals(Jsons.asJsonString(resp)));
    }

    @Test
    void test02_buscarPorIdElOdontologo() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/odontologos/buscar/{id}", 1))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Fulano"));
    }
}