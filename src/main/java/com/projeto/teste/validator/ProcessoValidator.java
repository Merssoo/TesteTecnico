package com.projeto.teste.validator;

import com.projeto.teste.repository.ProcessoRepository;
import com.projeto.teste.repository.ReuProcessoRepository;
import org.springframework.stereotype.Component;

@Component
public class ProcessoValidator {

    private final ProcessoRepository repository;
    private final ReuProcessoRepository reuProcessoRepository;

    public ProcessoValidator(ProcessoRepository repository, ReuProcessoRepository reuProcessoRepository) {
        this.repository = repository;
        this.reuProcessoRepository = reuProcessoRepository;
    }

    //Método valida se no banco de dados se ja existe um processo cadastrado com o numero do processo
    public void existeNumeroProcessoCadastrado(String numeroProcesso) {
        if(repository.existsAllByNumeroProcesso(numeroProcesso)) {
            throw new RuntimeException("O número de processo informado já está cadastrado no sistema.");
        }
    }

    //Método valida se o processo esta vinculado ao um reu
    public void isVinculadoProcesso(Long id) {
        if (reuProcessoRepository.existsByProcessoId(id)) {
            throw new RuntimeException("Não é possível excluir o processo, pois ele está vinculado a um réu.");
        }
    }

}
