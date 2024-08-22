package com.cd2tec.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class ShipmentDTO {

    @NotNull(message = "CEP de origem é obrigatório")
    @Schema(description = "CEP de origem", example = "01000-000")
    private String cepOrigem;

    @Schema(description = "Nome do remetente", example = "João da Silva")
    private String nomeRemetente;

    @NotNull(message = "CEP de destino é obrigatório")
    @Schema(description = "CEP de destino", example = "02000-000")
    private String cepDestino;

    @NotNull(message = "Nome do Remetente é obrigatório")
    @Schema(description = "Nome do destinatário", example = "Maria Oliveira")
    private String nomeDestino;

    @NotNull(message = "Peso do envio é obrigatório")
    @Schema(description = "Peso do envio em kg", example = "10.5")
    private Double peso;

    @Schema(description = "Flag indicando se é um envio real", example = "true")
    private boolean realInfo;

    // Getters e Setters

    public String getCepOrigem() {
        return removeDashes(cepOrigem);
    }

    public void setCepOrigem(String cepOrigem) {
        this.cepOrigem = removeDashes(cepOrigem);
    }

    public String getNomeRemetente() {
        return nomeRemetente;
    }

    public void setNomeRemetente(String nomeRemetente) {
        this.nomeRemetente = nomeRemetente;
    }

    public String getCepDestino() {
        return removeDashes(cepDestino);
    }

    public void setCepDestino(String cepDestino) {
        this.cepDestino = removeDashes(cepDestino);
    }

    public String getNomeDestino() {
        return nomeDestino;
    }

    public void setNomeDestino(String nomeDestino) {
        this.nomeDestino = nomeDestino;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public boolean isRealInfo() {
        return realInfo;
    }

    public void setRealInfo(boolean realInfo) {
        this.realInfo = realInfo;
    }

    private String removeDashes(String input) {
        return input != null ? input.replace("-", "") : null;
    }
}
