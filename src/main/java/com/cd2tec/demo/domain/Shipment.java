package com.cd2tec.demo.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "shipment")
public class Shipment implements Serializable {
    private static final long serialVersionUID = 1L; 

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    
    @Column(name = "cep_origem")
    private String cepOrigem;
    
    @Column(name = "nome_remetente", nullable = true)
    private String nomeRemetente;

    @Column(name = "cep_destino")
    private String cepDestino;

    @Column(name = "nome_destino", nullable = true)
    private String nomeDestino;
    
    @Column(name = "peso")
    private Double peso;
    
    @Column(name = "data_consulta")
    private ZonedDateTime dtConsulta;
    
    @Column(name = "data_prevista_entrega")
    private ZonedDateTime dtPrevistaEntrega;
    
    @Column(name = "valor_total_frete")
    private Double vlTotalFrete;

    @Column(name = "is_mock")
    private boolean isMock;

    public Shipment(){}

    public Shipment(String cepOrigem, String nomeRemetente, String cepDestino, String nomeDestino, Double peso, ZonedDateTime dtConsulta, ZonedDateTime dtPrevistaEntrega, Double vlTotalFrete, boolean isMock) {
        this.cepOrigem = cepOrigem;
        this.nomeRemetente = nomeRemetente;
        this.cepDestino = cepDestino;
        this.nomeDestino = nomeDestino;
        this.peso = peso;
        this.dtConsulta = dtConsulta;
        this.dtPrevistaEntrega = dtPrevistaEntrega;
        this.vlTotalFrete = vlTotalFrete;
        this.isMock = isMock;
    }

    public boolean isMock() {
        return isMock;
    }

    public void setMock(boolean mock) {
        isMock = mock;
    }

    public Double getVlTotalFrete() {
        return vlTotalFrete;
    }

    public void setVlTotalFrete(Double vlTotalFrete) {
        this.vlTotalFrete = vlTotalFrete;
    }

    public ZonedDateTime getDtPrevistaEntrega() {
        return dtPrevistaEntrega;
    }

    public void setDtPrevistaEntrega(ZonedDateTime dtPrevistaEntrega) {
        this.dtPrevistaEntrega = dtPrevistaEntrega;
    }

    public ZonedDateTime getDtConsulta() {
        return dtConsulta;
    }

    public void setDtConsulta(ZonedDateTime dtConsulta) {
        this.dtConsulta = dtConsulta;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getNomeDestino() {
        return nomeDestino;
    }

    public void setNomeDestino(String nomeDestino) {
        this.nomeDestino = nomeDestino;
    }

    public String getCepDestino() {
        return cepDestino;
    }

    public void setCepDestino(String cepDestino) {
        this.cepDestino = cepDestino;
    }

    public String getNomeRemetente() {
        return nomeRemetente;
    }

    public void setNomeRemetente(String nomeRemetente) {
        this.nomeRemetente = nomeRemetente;
    }

    public String getCepOrigem() {
        return cepOrigem;
    }

    public void setCepOrigem(String cepOrigem) {
        this.cepOrigem = cepOrigem;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shipment shipment = (Shipment) o;
        return isMock == shipment.isMock && Objects.equals(id, shipment.id) && Objects.equals(cepOrigem, shipment.cepOrigem) && Objects.equals(nomeRemetente, shipment.nomeRemetente) && Objects.equals(cepDestino, shipment.cepDestino) && Objects.equals(nomeDestino, shipment.nomeDestino) && Objects.equals(peso, shipment.peso) && Objects.equals(dtConsulta, shipment.dtConsulta) && Objects.equals(dtPrevistaEntrega, shipment.dtPrevistaEntrega) && Objects.equals(vlTotalFrete, shipment.vlTotalFrete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cepOrigem, nomeRemetente, cepDestino, nomeDestino, peso, dtConsulta, dtPrevistaEntrega, vlTotalFrete, isMock);
    }
}
