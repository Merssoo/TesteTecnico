package com.projeto.teste.service;

import com.projeto.teste.dto.ReuProcessoDTO;
import com.projeto.teste.mapper.ReuProcessoMapper;
import com.projeto.teste.model.Processo;
import com.projeto.teste.model.Reu;
import com.projeto.teste.model.ReuProcesso;
import com.projeto.teste.repository.ProcessoRepository;
import com.projeto.teste.repository.ReuProcessoRepository;
import com.projeto.teste.repository.ReuRepository;
import com.projeto.teste.validator.ReuProcessoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReuProcessoService {

    private final ReuProcessoRepository repository;
    private final ReuProcessoMapper mapper;
    private final ReuProcessoValidator validator;
    private final ProcessoRepository processoRepository;
    private final ReuRepository reuRepository;

    @Autowired
    public ReuProcessoService(ReuProcessoRepository repository, ReuProcessoMapper mapper, ReuProcessoValidator validator, ProcessoRepository processoRepository, ReuRepository reuRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
        this.processoRepository = processoRepository;
        this.reuRepository = reuRepository;
    }

    //Método que valida e aplica regras para salvar um reuProcesso
    public ReuProcessoDTO salvar(ReuProcessoDTO dto) {
        validator.existeReuProcessoCadastrado(dto.getReuDTO().getId(), dto.getProcessoDTO().getId());
        Reu reu = reuRepository.findById(dto.getReuDTO().getId())
                .orElseThrow(() -> new RuntimeException("Réu não encontrado"));

        Processo processo = processoRepository.findById(dto.getProcessoDTO().getId())
                .orElseThrow(() -> new RuntimeException("Processo não encontrado"));

        ReuProcesso reuProcesso = mapper.toEntity(dto);
        reuProcesso.setProcesso(processo);
        reuProcesso.setReu(reu);

        return mapper.toDTO(repository.save(reuProcesso));
    }

    //Método que busca todos os Reuprocessos
    public Page<ReuProcessoDTO> buscarTodos(Pageable pageable) {
        return repository.findAll(pageable)
                .map(reuProcesso -> mapper.toDTO(reuProcesso));
    }

    //Método que valida e busca um unico reuProcesso com base no ID
    public ReuProcessoDTO buscar(Long id) {
        ReuProcesso reuProcesso = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reu não encontrado para o id: " + id));
        return mapper.toDTO(reuProcesso);
    }

    //Método que valida e aplica regras de negocio para atualizar um reuProcesso com base no seu ID
    public ReuProcessoDTO atualizar(Long id, ReuProcessoDTO dto) {
        validator.existeReuProcessoCadastrado(dto.getReuDTO().getId(), dto.getProcessoDTO().getId());
        ReuProcesso reuProcesso = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reu não encontrado para o id: " + id));

        Reu reu = reuRepository.findById(dto.getReuDTO().getId())
                .orElseThrow(() -> new RuntimeException("Réu não encontrado"));

        Processo processo = processoRepository.findById(dto.getProcessoDTO().getId())
                .orElseThrow(() -> new RuntimeException("Processo não encontrado"));

        reuProcesso.setReu(reu);
        reuProcesso.setProcesso(processo);
        return mapper.toDTO(repository.save(reuProcesso));
    }

    //Método que valida e exclui um reuProcesso com base no ID
    public void excluir(Long id) {
        ReuProcesso reuProcesso = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reu não encontrado para o id: " + id));
        repository.delete(reuProcesso);
    }
}
