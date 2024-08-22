package com.cd2tec.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressDTO {

    @JsonProperty("logradouro")
    private String street;

    @JsonProperty("localidade")
    private String city;

    @JsonProperty("ddd")
    private Integer ddd;

    @JsonProperty("uf")
    private String uf;

    // Getters e Setters
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getDdd() {
        return ddd;
    }

    public void setDdd(Integer ddd) {
        this.ddd = ddd;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

}
