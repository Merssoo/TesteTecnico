package com.projeto.teste.repository;

import com.projeto.teste.model.ReuProcesso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReuProcessoRepository extends JpaRepository<ReuProcesso, Long> {

    //Método verifica no banco se ja existe um vinculo de reu e processo
    boolean existsByReuIdAndProcessoId(Long reuId, Long processoId);

    //Método verifica se o reu esta vinculado ao um processo
    boolean existsByReuId(Long reuId);

    //Método verifica se o processo esta vinculado ao um reu
    boolean existsByProcessoId(Long processoId);

    //Método aplica paginação a consulta findAll
    @Override
    Page<ReuProcesso> findAll(Pageable pageable);
}
