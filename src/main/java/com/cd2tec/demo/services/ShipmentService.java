package com.cd2tec.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cd2tec.demo.domain.Shipment;
import com.cd2tec.demo.dto.ShipmentDTO;
import com.cd2tec.demo.exceptions.handler.ResourceBadRequestException;
import com.cd2tec.demo.exceptions.handler.ResourceNotFoundException;
import com.cd2tec.demo.repositories.ShipmentRepository;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private CepValidationService cepValidationService;

    @Autowired
    private CostCalculationService costCalculationService;

    @Autowired
    private TimeCalculationService timeCalculationService;

    private static final Logger logger = LoggerFactory.getLogger(ShipmentService.class);

    public Shipment getShipmentById(UUID id) {
        logger.info("Finding a shipping record");
        return shipmentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Shipment not found!"));
    }

    public List<Shipment> getListShipment() {
        logger.info("Finding a list of shipping records");
        return shipmentRepository.findAll();
    }

    public Shipment createShipment(ShipmentDTO shipmentDTO) {
        logger.info("Creating one shipment");

        validateShipmentData(shipmentDTO);

        double cost;
        ZonedDateTime sendTime;

        if (shipmentDTO.isRealInfo()) {
            cost = costCalculationService.calculateShippingCost(
                    shipmentDTO.getCepOrigem(),
                    shipmentDTO.getCepDestino(),
                    shipmentDTO.getPeso()
            );

            sendTime = timeCalculationService.calculateTimeCost(
                    shipmentDTO.getCepOrigem(),
                    shipmentDTO.getCepDestino()
            );
        } else {
            cost = costCalculationService.calculateShippingCostFalse(
                    shipmentDTO.getCepOrigem(),
                    shipmentDTO.getCepDestino(),
                    shipmentDTO.getPeso()
            );

            sendTime = timeCalculationService.calculateTimeCostFalse(
                    shipmentDTO.getCepOrigem(),
                    shipmentDTO.getCepDestino()
            );
        }

        Shipment shipment = new Shipment(
            shipmentDTO.getCepOrigem(),
            shipmentDTO.getNomeRemetente(),
            shipmentDTO.getCepDestino(),
            shipmentDTO.getNomeDestino(),
            shipmentDTO.getPeso(),
            ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")),
            sendTime,
            cost,
            shipmentDTO.isRealInfo()
        );

        try {
            return shipmentRepository.save(shipment);
        } catch (Exception e) {
            logger.error("Error saving shipment", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving shipment", e);
        }
    }

    public void deleteShipment(UUID id) {
        logger.info("Deleting a shipping record");
        Shipment entity = shipmentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Shipment not found!"));
        shipmentRepository.delete(entity);
    }

    private void validateShipmentData(ShipmentDTO shipmentDTO) {
        // Validar CEPs
        if (shipmentDTO.getCepOrigem() == null || shipmentDTO.getCepOrigem().isEmpty()) {
            throw new ResourceBadRequestException("CEP de origem não pode ser vazio.");
        }
        cepValidationService.validateCep(shipmentDTO.getCepOrigem());

        if (shipmentDTO.getCepDestino() == null || shipmentDTO.getCepDestino().isEmpty()) {
            throw new ResourceBadRequestException("CEP de destino não pode ser vazio.");
        }
        cepValidationService.validateCep(shipmentDTO.getCepDestino());

        // Validar peso
        if (shipmentDTO.getPeso() == null) {
            throw new ResourceBadRequestException("Peso não pode ser nulo.");
        }
        if (shipmentDTO.getPeso() < 0) {
            throw new ResourceBadRequestException("Peso deve ser maior ou igual a zero.");
        }
    }
}
