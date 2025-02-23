package com.projeto.teste.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.teste.dto.ProcessoDTO;
import com.projeto.teste.dto.ReuDTO;
import com.projeto.teste.dto.ReuProcessoDTO;
import com.projeto.teste.model.Processo;
import com.projeto.teste.model.Reu;
import com.projeto.teste.model.ReuProcesso;
import com.projeto.teste.repository.ReuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ReuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ReuRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();

        Reu reu = new Reu();
        reu.setNome("João");
        reu.setCpf("123.456.789-00");
        repository.save(reu);
    }

    @Test
    @DisplayName("Deve criar um novo reu com sucesso")
    public void deveCriarReu() throws Exception {

        ReuDTO dto = new ReuDTO();
        dto.setNome("Maria");
        dto.setCpf("123.456.789-21");

        mockMvc.perform(post("/reu")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Deve deletar um reu com sucesso")
    public void deveDeletarReu() throws Exception {
        Reu reu = repository.findAll().get(0);

        mockMvc.perform(delete("/reu/{id}", reu.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve buscar um reu por ID")
    public void deveBuscarReuPorId() throws Exception {
        Reu reu = repository.findAll().get(0);

        mockMvc.perform(get("/reu/{id}", reu.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(reu.getNome()))
                .andExpect(jsonPath("$.cpf").value(reu.getCpf()));
    }

    @Test
    @DisplayName("Deve listar todos os reus")
    public void deveListarTodosOsReus() throws Exception {
        mockMvc.perform(get("/reu"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1));
    }

    @Test
    @DisplayName("Deve atualizar um reu")
    public void deveAtualizarReu() throws Exception {
        Reu reu = repository.findAll().get(0);
        ReuDTO dto = new ReuDTO();
        dto.setCpf("123.456.789-15");
        dto.setNome(reu.getNome());
        dto.setId(reu.getId());

        mockMvc.perform(put("/reu/{id}", reu.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf").value(dto.getCpf()));

    }
}
