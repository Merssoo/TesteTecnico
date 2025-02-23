package com.projeto.teste.service;

import com.projeto.teste.validator.ProcessoValidator;
import com.projeto.teste.dto.ProcessoDTO;
import com.projeto.teste.mapper.ProcessoMapper;
import com.projeto.teste.model.Processo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.projeto.teste.repository.ProcessoRepository;

@Service
public class ProcessoService {

    private final ProcessoRepository repository;
    private final ProcessoMapper mapper;
    private final ProcessoValidator validator;

    @Autowired
    public ProcessoService(ProcessoRepository repository, ProcessoMapper mapper, ProcessoValidator validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    //Método que valida e aplica regras para salvar um processo
    public ProcessoDTO salvar(ProcessoDTO dto) {
        validator.existeNumeroProcessoCadastrado(dto.getNumeroProcesso());
        Processo processo = repository.save(mapper.toEntity(dto));
        return mapper.toDTO(processo);
    }

    //Método que busca todos os processos
    public Page<ProcessoDTO> buscarTodos(Pageable pageable) {
        return repository.findAll(pageable)
                .map(processo -> mapper.toDTO(processo));
    }

    //Método que valida e busca um unico processo com base no ID
    public ProcessoDTO buscar(Long id) {
        Processo processo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Processo não encontrado para o id: " + id));
        return mapper.toDTO(processo);
    }

    //Método que valida e aplica regras de negocio para atualizar um processo com base no seu ID
    public ProcessoDTO atualizar(Long id, ProcessoDTO dto) {
        validator.existeNumeroProcessoCadastrado(dto.getNumeroProcesso());
        Processo processo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Processo não encontrado para o id: " + id));

        processo.setNumeroProcesso(dto.getNumeroProcesso());
        return mapper.toDTO(repository.save(processo));
    }

    //Método que valida e exclui um processo com base no ID
    public void excluir(Long id) {
        Processo processo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Processo não encontrado com o ID: " + id));
        validator.isVinculadoProcesso(id);
        repository.delete(processo);
    }
}
