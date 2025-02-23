package com.projeto.teste.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.teste.dto.ProcessoDTO;
import com.projeto.teste.dto.ReuDTO;
import com.projeto.teste.dto.ReuProcessoDTO;
import com.projeto.teste.model.Processo;
import com.projeto.teste.model.Reu;
import com.projeto.teste.model.ReuProcesso;
import com.projeto.teste.repository.ProcessoRepository;
import com.projeto.teste.repository.ReuProcessoRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ReuProcessoControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ReuRepository reuRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private ReuProcessoRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        reuRepository.deleteAll();
        processoRepository.deleteAll();

        Reu reu = new Reu();
        reu.setNome("João");
        reu.setCpf("123.456.789-00");
        Reu reuSalvo = reuRepository.save(reu);

        Processo processo = new Processo();
        processo.setNumeroProcesso("0000004-00.0000.0.00.0000");
        processo.setDataCriacao(LocalDateTime.now());
        Processo processoSalvo = processoRepository.save(processo);

        ReuProcesso reuProcesso = new ReuProcesso();
        reuProcesso.setReu(reuSalvo);
        reuProcesso.setProcesso(processoSalvo);
        repository.save(reuProcesso);
    }

    @Test
    @DisplayName("Deve criar um novo ReuProcesso com sucesso")
    public void deveCriarReuProcesso() throws Exception {

        Reu reu = reuRepository.findAll().get(0);
        Processo processo = new Processo();
        processo.setNumeroProcesso("0000005-00.0000.0.00.0000");
        processo.setDataCriacao(LocalDateTime.now());
        Processo processoSalvo = processoRepository.save(processo);

        ReuDTO reuDTO = new ReuDTO();
        reuDTO.setId(reu.getId());

        ProcessoDTO processoDTO = new ProcessoDTO();
        processoDTO.setId(processoSalvo.getId());

        ReuProcessoDTO dto = new ReuProcessoDTO();
        dto.setReuDTO(reuDTO);
        dto.setProcessoDTO(processoDTO);

        mockMvc.perform(post("/reu-processo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Deve deletar um ReuProcesso com sucesso")
    public void deveDeletarReuProcesso() throws Exception {
        ReuProcesso reuProcesso = repository.findAll().get(0);

        mockMvc.perform(delete("/reu-processo/{id}", reuProcesso.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve buscar um ReuProcesso por ID")
    public void deveBuscarReuProcessoPorId() throws Exception {
        ReuProcesso reuProcesso = repository.findAll().get(0);

        mockMvc.perform(get("/reu-processo/{id}", reuProcesso.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reu.id").value(reuProcesso.getReu().getId()))
                .andExpect(jsonPath("$.processo.id").value(reuProcesso.getProcesso().getId()));
    }

    @Test
    @DisplayName("Deve listar todos os ReuProcesso")
    public void deveListarTodosOsReuProcesso() throws Exception {
        mockMvc.perform(get("/reu-processo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1));
    }

    @Test
    @DisplayName("Deve atualizar um ReuProcesso")
    public void deveAtualizarReuProcesso() throws Exception {
        ReuProcesso reuProcesso = repository.findAll().get(0);

        Processo novoProcesso = new Processo();
        novoProcesso.setNumeroProcesso("9999999-99.9999.9.99.9999");
        novoProcesso.setDataCriacao(LocalDateTime.now());
        novoProcesso = processoRepository.save(novoProcesso);

        ReuProcessoDTO dto = new ReuProcessoDTO();
        dto.setId(reuProcesso.getId());

        ReuDTO reuDTO = new ReuDTO();
        reuDTO.setId(reuProcesso.getReu().getId());

        ProcessoDTO processoDTO = new ProcessoDTO();
        processoDTO.setId(novoProcesso.getId());

        dto.setProcessoDTO(processoDTO);
        dto.setReuDTO(reuDTO);

        mockMvc.perform(put("/reu-processo/{id}", reuProcesso.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.processo.id").value(novoProcesso.getId()));

    }
}

