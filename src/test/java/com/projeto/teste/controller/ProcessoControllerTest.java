package com.projeto.teste.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.teste.dto.ProcessoDTO;
import com.projeto.teste.model.Processo;
import com.projeto.teste.repository.ProcessoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProcessoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProcessoRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();

        Processo processo = new Processo();
        processo.setNumeroProcesso("0000004-00.0000.0.00.0000");
        processo.setDataCriacao(LocalDateTime.now());
        repository.save(processo);
    }

    @Test
    @DisplayName("Deve criar um novo processo com sucesso")
    public void deveCriarProcesso() throws Exception {

        ProcessoDTO dto = new ProcessoDTO();
        dto.setNumeroProcesso("0000051-00.0000.0.00.0000");
        dto.setDataCriacao(LocalDateTime.now());

        mockMvc.perform(post("/processos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Deve deletar um processo com sucesso")
    public void deveDeletarProcesso() throws Exception {
        Processo processo = repository.findAll().get(0);

        mockMvc.perform(delete("/processos/{id}", processo.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve buscar um processo por ID")
    public void deveBuscarProcessoPorId() throws Exception {
        Processo processo = repository.findAll().get(0);
        String dataEsperada = processo.getDataCriacao().toString();

        mockMvc.perform(get("/processos/{id}", processo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numero_processo").value(processo.getNumeroProcesso()))
                .andExpect(jsonPath("$.dataCriacao").value(anyOf(
                        equalTo(dataEsperada),
                        matchesPattern("\\d{4}-\\d{2}-\\d{2}[ T]\\d{2}:\\d{2}:\\d{2}")
                )));
    }

    @Test
    @DisplayName("Deve listar todos os processos")
    public void deveListarTodosOsProcessos() throws Exception {
        mockMvc.perform(get("/processos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1));
    }

    @Test
    @DisplayName("Deve atualizar um processo")
    public void deveAtualizarProcesso() throws Exception {
        Processo processo = repository.findAll().get(0);

        ProcessoDTO dto = new ProcessoDTO();
        dto.setNumeroProcesso("0000008-00.0000.0.00.0000");
        dto.setDataCriacao(processo.getDataCriacao());
        dto.setId(processo.getId());

        mockMvc.perform(put("/processos/{id}", processo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numero_processo").value(dto.getNumeroProcesso()));

    }
}
