package com.projeto.teste.repository;

import com.projeto.teste.model.Processo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {

    //Método que verifica no banco se ja existe um processo com o numero em questão
    boolean existsAllByNumeroProcesso(String numeroProcesso);

    //Método aplica paginação a consulta findAll
    @Override
    Page<Processo> findAll(Pageable pageable);
}
