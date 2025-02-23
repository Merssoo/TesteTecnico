package com.projeto.teste.repository;

import com.projeto.teste.model.Reu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReuRepository extends JpaRepository<Reu, Long> {

    //Método que verifica no banco se ja existe um reu com o cpf em questão
    boolean existsAllByCpf(String cpf);

    //Método aplica paginação a consulta findAll
    @Override
    Page<Reu> findAll(Pageable pageable);
}
