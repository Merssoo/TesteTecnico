package com.projeto.teste.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reu_processo")
public class ReuProcesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reu_id", nullable = false)
    private Reu reu;

    @ManyToOne
    @JoinColumn(name = "processo_id", nullable = false)
    private Processo processo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reu getReu() {
        return reu;
    }

    public void setReu(Reu reu) {
        this.reu = reu;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }
}
