package com.cd2tec.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.cd2tec.demo.exceptions.handler.ResourceBadRequestException;

@Service
public class DistanceService {

    @Autowired
    private CepValidationService cepValidationService;

    @Autowired
    private GeocodingService geocodingService;

    @Autowired
    private DistanceCalculator distanceCalculator;

    public double getDistanceBetweenCeps(String cep1, String cep2) {
        // Validar CEPs
        validateCep(cep1);
        validateCep(cep2);

        // Obter endereços usando os CEPs
        String address1 = cepValidationService.getAddress(cep1);
        String address2 = cepValidationService.getAddress(cep2);

        // Obter coordenadas usando os endereços
        double[] coordinates1 = geocodingService.getCoordinates(address1);
        double[] coordinates2 = geocodingService.getCoordinates(address2);

        // Calcular e retornar a distância
        return distanceCalculator.calculateDistance(
            coordinates1[0], coordinates1[1],
            coordinates2[0], coordinates2[1]
        );
    }

    private void validateCep(String cep) {
        if (cep == null || cep.isEmpty()) {
            throw new ResourceBadRequestException("CEP não pode ser vazio.");
        }
        cepValidationService.validateCep(cep);
    }
}
