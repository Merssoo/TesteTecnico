package com.projeto.teste.controller;

import com.projeto.teste.dto.ReuProcessoDTO;
import com.projeto.teste.service.ReuProcessoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/reu-processo", produces = "application/json")
public class ReuProcessoController {


    @Autowired
    private ReuProcessoService service;

    //Método para salvar um novo reuProcesso
    @PostMapping
    public ResponseEntity<ReuProcessoDTO> salvar(@Valid @RequestBody ReuProcessoDTO dto) {
        ReuProcessoDTO processoSalvo = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(processoSalvo);
    }

    //Método para buscar todos os reuProcessos
    @GetMapping
    public Page<ReuProcessoDTO> buscarTodos(Pageable pageable) {
        return service.buscarTodos(pageable);
    }

    //Método para buscar reuProcesso pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<ReuProcessoDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    //Método para atualizar um reuProcesso
    @PutMapping("/{id}")
    public ResponseEntity<ReuProcessoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ReuProcessoDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    //Método para deletar um reuProcesso pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
