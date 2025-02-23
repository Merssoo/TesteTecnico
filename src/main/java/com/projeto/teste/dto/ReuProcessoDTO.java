package com.projeto.teste.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class ReuProcessoDTO {

    private Long id;

    @NotNull(message = "Por favor, informe um réu para continuar.")
    @JsonProperty("reu")
    ReuDTO reuDTO;

    @NotNull(message = "Por favor, informe um processo para continuar.")
    @JsonProperty("processo")
    ProcessoDTO processoDTO;

    public ReuProcessoDTO() {
    }

    public ReuProcessoDTO(Long id, ReuDTO reuDTO, ProcessoDTO processoDTO) {
        this.id = id;
        this.reuDTO = reuDTO;
        this.processoDTO = processoDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReuDTO getReuDTO() {
        return reuDTO;
    }

    public void setReuDTO(ReuDTO reuDTO) {
        this.reuDTO = reuDTO;
    }

    public ProcessoDTO getProcessoDTO() {
        return processoDTO;
    }

    public void setProcessoDTO(ProcessoDTO processoDTO) {
        this.processoDTO = processoDTO;
    }
}
