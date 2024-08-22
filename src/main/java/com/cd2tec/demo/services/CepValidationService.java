package com.cd2tec.demo.services;

import com.cd2tec.demo.dto.AddressDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.cd2tec.demo.exceptions.handler.ResourceBadRequestException;

@Service
public class CepValidationService {

    private final RestTemplate restTemplate;

    public CepValidationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void validateCep(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                if (response.getBody().contains("\"erro\": true")) {
                    throw new ResourceBadRequestException("CEP inválido: " + cep);
                }
            } else {
                throw new ResourceBadRequestException("Erro ao consultar o CEP: " + cep);
            }
        } catch (Exception e) {
            throw new ResourceBadRequestException("Erro ao consultar o CEP: " + cep);
        }
    }

    public String getAddress(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        System.out.println(url);
        AddressDTO addressDTO = restTemplate.getForObject(url, AddressDTO.class);

        if (addressDTO == null) {
            throw new RuntimeException("Address not found");
        }

        // Combine the `logradouro` and `localidade` fields
        return addressDTO.getStreet() + ", " + addressDTO.getCity();
    }

    public Integer getDDD(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        AddressDTO addressDTO = restTemplate.getForObject(url, AddressDTO.class);

        if (addressDTO == null) {
            throw new RuntimeException("Address not found");
        }

        return addressDTO.getDdd();
    }

    public String getUF(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        AddressDTO addressDTO = restTemplate.getForObject(url, AddressDTO.class);

        if (addressDTO == null) {
            throw new RuntimeException("Address not found");
        }

        return addressDTO.getUf();
    }
}
