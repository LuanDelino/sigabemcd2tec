package com.cd2tec.demo.services;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TimeCalculationService {

    @Autowired
    private DistanceService distanceService;

    @Autowired
    private CostCalculationService costCalculationService;

    private static final Logger logger = LoggerFactory.getLogger(ShipmentService.class);

    // Velocidade média de transporte em km/h
    private static final double AVERAGE_SPEED_KMH = 60.0;

    /**
     * Calcula o tempo de envio com base na distância entre dois CEPs.
     *
     * @param cepOrigem CEP de origem
     * @param cepDestino CEP de destino
     * @return Tempo estimado de envio em horas
     */
    public ZonedDateTime calculateTimeCost(String cepOrigem, String cepDestino) {
        Integer varianceDays = 2;
        double distance = distanceService.getDistanceBetweenCeps(cepOrigem, cepDestino);

        double timeHours = distance / AVERAGE_SPEED_KMH;

        // Definindo o fuso horário de Brasília
        ZoneId zoneIdBrasilia = ZoneId.of("America/Sao_Paulo");

        return ZonedDateTime.now(zoneIdBrasilia).plusSeconds((long) (timeHours * 3600 + varianceDays * 24 *3600));
    }

    public ZonedDateTime calculateTimeCostFalse(String cepOrigem, String cepDestino) {
        int varianceDays;
        double distance = distanceService.getDistanceBetweenCeps(cepOrigem, cepDestino);

        if (costCalculationService.isSameDDD(cepOrigem, cepDestino)) {
            logger.info("Local é o mesmo DDD!");
            varianceDays = 1;
        } else if (costCalculationService.isSameState(cepOrigem, cepDestino)) {
            logger.info("Local é o mesmo Estado!");
            varianceDays = 3;
        } else {
            varianceDays = 10;
        }

        double timeHours = distance / AVERAGE_SPEED_KMH;
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(varianceDays);
    }
}
