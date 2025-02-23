package com.projeto.teste.validator;

import com.projeto.teste.repository.ReuProcessoRepository;
import org.springframework.stereotype.Component;

@Component
public class ReuProcessoValidator {

    private final ReuProcessoRepository repository;

    public ReuProcessoValidator(ReuProcessoRepository repository) {
        this.repository = repository;
    }

    //Método valida se existe no banco um proceso e reu vinculados
    public void existeReuProcessoCadastrado(Long reuId, Long processoId) {
        if (repository.existsByReuIdAndProcessoId(reuId, processoId)) {
            throw new RuntimeException("Este réu já está vinculado a este processo. Verifique os dados e tente novamente.");
        }
    }
}
