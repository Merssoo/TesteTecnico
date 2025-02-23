package com.projeto.teste.controller;

import com.projeto.teste.dto.ReuDTO;
import com.projeto.teste.service.ReuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/reu", produces = "application/json")
public class ReuController {

    @Autowired
    private ReuService service;

    //Método para salvar um novo réu
    @PostMapping
    public ResponseEntity<ReuDTO> salvar(@Valid @RequestBody ReuDTO dto) {
        ReuDTO processoSalvo = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(processoSalvo);
    }

    //Método para buscar todos os reus
    @GetMapping
    public Page<ReuDTO> buscarTodos(Pageable pageable) {
        return service.buscarTodos(pageable);
    }

    //Método para buscar reu pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<ReuDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    //Método para atualizar um réu
    @PutMapping("/{id}")
    public ResponseEntity<ReuDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ReuDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    //Método para deletar um processo pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
