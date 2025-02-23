package com.projeto.teste.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public class ProcessoDTO {

        private Long id;

        @NotBlank(message = "O número do processo é obrigatório")
        @Pattern(regexp = "\\d{7}-\\d{2}\\.\\d{4}\\.\\d\\.\\d{2}\\.\\d{4}", message = "Formato inválido. Use: 0000000-00.0000.0.00.0000")
        @JsonProperty("numero_processo")
        private String numeroProcesso;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime dataCriacao = LocalDateTime.now();

        public ProcessoDTO() {
        }

        public ProcessoDTO(Long id, String numeroProcesso, LocalDateTime dataCriacao) {
                this.id = id;
                this.numeroProcesso = numeroProcesso;
                this.dataCriacao = dataCriacao;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getNumeroProcesso() {
                return numeroProcesso;
        }

        public void setNumeroProcesso(String numeroProcesso) {
                this.numeroProcesso = numeroProcesso;
        }

        public LocalDateTime getDataCriacao() {
                return dataCriacao;
        }

        public void setDataCriacao(LocalDateTime dataCriacao) {
                this.dataCriacao = dataCriacao;
        }
}
