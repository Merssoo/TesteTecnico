package com.projeto.teste.controller;

import com.projeto.teste.dto.ProcessoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.projeto.teste.service.ProcessoService;

@RestController
@RequestMapping(value = "/processos", produces = "application/json")
public class ProcessoController {

    @Autowired
    private ProcessoService service;

    //Método para salvar um novo processo
    @PostMapping
    public ResponseEntity<ProcessoDTO> salvar(@Valid @RequestBody ProcessoDTO dto) {
        ProcessoDTO processoSalvo = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(processoSalvo);
    }

    //Método para buscar todos processos
    @GetMapping
    public Page<ProcessoDTO> buscarTodos(Pageable pageable) {
        return service.buscarTodos(pageable);
    }

    //Método para buscar um processo pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<ProcessoDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    //Método para editar um processo
    @PutMapping("/{id}")
    public ResponseEntity<ProcessoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ProcessoDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    //Método para deletar um processo pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
