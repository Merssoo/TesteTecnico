package com.projeto.teste.validator;

import com.projeto.teste.repository.ReuProcessoRepository;
import com.projeto.teste.repository.ReuRepository;
import org.springframework.stereotype.Component;

@Component
public class ReuValidator {

    private final ReuRepository repository;
    private final ReuProcessoRepository reuProcessoRepository;

    public ReuValidator(ReuRepository repository, ReuProcessoRepository reuProcessoRepository) {
        this.repository = repository;
        this.reuProcessoRepository = reuProcessoRepository;
    }
    //Método valida se no banco de dados se ja existe um reu cadastrado com o cpf
    public void existeCpfCadastrado(String cpf) {
        if (repository.existsAllByCpf(cpf)) {
            throw new RuntimeException("Já existe um reu com o CPF: " + cpf);
        }
    }

    //Método valida se o reu esta vinculado ao um processo
    public void isVinculadoProcesso(Long id) {
        if (reuProcessoRepository.existsByReuId(id)) {
            throw new RuntimeException("Não é possível excluir o réu, pois ele está vinculado a um processo.");
        }
    }
}
