package com.projeto.teste.service;

import com.projeto.teste.dto.ReuDTO;
import com.projeto.teste.mapper.ReuMapper;
import com.projeto.teste.model.Reu;
import com.projeto.teste.repository.ReuRepository;
import com.projeto.teste.validator.ReuValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReuService {

    private final ReuRepository repository;
    private final ReuMapper mapper;
    private final ReuValidator validator;

    @Autowired
    public ReuService(ReuRepository repository, ReuMapper mapper, ReuValidator validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    //Método que valida e aplica regras para salvar um reu
    public ReuDTO salvar(ReuDTO dto) {
        validator.existeCpfCadastrado(dto.getCpf());
        Reu reu = repository.save(mapper.toEntity(dto));
        return mapper.toDTO(reu);
    }

    //Método que busca todos os reu
    public Page<ReuDTO> buscarTodos(Pageable pageable) {
        return repository.findAll(pageable)
                .map(reu -> mapper.toDTO(reu));
    }

    //Método que valida e busca um unico reu com base no ID
    public ReuDTO buscar(Long id) {
        Reu reu = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reu não encontrado para o id: " + id));
        return mapper.toDTO(reu);
    }

    //Método que valida e aplica regras de negocio para atualizar um reu com base no seu ID
    public ReuDTO atualizar(Long id, ReuDTO dto) {
        validator.existeCpfCadastrado(dto.getCpf());
        Reu reu = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reu não encontrado para o id: " + id));

        mapper.atualizarReuComDTO(dto, reu);
        return mapper.toDTO(repository.save(reu));
    }

    //Método que valida e exclui um reu com base no ID
    public void excluir(Long id) {
        Reu reu = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reu não encontrado para o id: " + id));
        validator.isVinculadoProcesso(id);
        repository.delete(reu);
    }
}
