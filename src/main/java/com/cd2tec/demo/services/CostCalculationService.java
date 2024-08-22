package com.cd2tec.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class CostCalculationService {

    @Autowired
    private DistanceService distanceService;

    @Autowired
    private CepValidationService cepValidationService;

    // Em caso de busca de informações verdadeiras
    public double calculateShippingCost(String cepOrigem, String cepDestino, Double peso) {
        double distance = calculateDistance(cepOrigem, cepDestino);
        double costPerKg = 1;
        double cost = distance * costPerKg * peso;

        BigDecimal bd = new BigDecimal(cost);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private double calculateDistance(String cepOrigem, String cepDestino) {
        return distanceService.getDistanceBetweenCeps(cepOrigem, cepDestino);
    }

    // Em caso de busca de informações mock
    public double calculateShippingCostFalse(String cepOrigem, String cepDestino, Double peso) {
        double distance = calculateDistance(cepOrigem, cepDestino);
        double costPerKg = 1;
        double discount = 0;

        if (isSameDDD(cepOrigem, cepDestino)) {
            discount = 0.50;
        } else if (isSameState(cepOrigem, cepDestino)) {
            discount = 0.75;
        }

        double cost = distance * costPerKg * peso * (1 - discount);

        BigDecimal bd = new BigDecimal(cost);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    boolean isSameDDD(String cepOrigem, String cepDestino) {
        Integer cepOrigemddd = cepValidationService.getDDD(cepOrigem);
        Integer cepDestinoddd = cepValidationService.getDDD(cepDestino);
        return cepOrigemddd == cepDestinoddd;
    }

    boolean isSameState(String cepOrigem, String cepDestino) {
        String cepOrigemUF = cepValidationService.getUF(cepOrigem);
        String cepDestinoUF = cepValidationService.getUF(cepDestino);
        return cepOrigemUF.equals(cepDestinoUF);
    }
}
