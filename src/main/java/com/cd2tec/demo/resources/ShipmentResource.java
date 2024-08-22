package com.cd2tec.demo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.cd2tec.demo.domain.Shipment;
import com.cd2tec.demo.dto.ShipmentDTO;
import com.cd2tec.demo.model.APIResponse;
import com.cd2tec.demo.services.ShipmentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shipment")
public class ShipmentResource {

    @Autowired
    private ShipmentService shipmentService;

    @Operation(summary = "Obter um envio por ID", description = "Retorna um envio específico baseado no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envio encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Envio não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Shipment>> getShipmentById(@PathVariable UUID id) {
        Shipment shipment = shipmentService.getShipmentById(id);
        APIResponse<Shipment> response = new APIResponse<>(
                "Shipment found successfully",
                HttpStatus.OK.value(),
                List.of(shipment)
        );
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Obter uma lista de envio", description = "Retorna uma lista de envios.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de envios recuperada com sucesso")
    })
    @GetMapping
    public ResponseEntity<APIResponse<Shipment>> getListShipment() {
        List<Shipment> shipments = shipmentService.getListShipment();
        APIResponse<Shipment> response = new APIResponse<>(
                "List of shipments retrieved successfully",
                HttpStatus.OK.value(),
                shipments
        );
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Registrar um envio", description = "Realiza um registro de envio.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Envio criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Solicitação inválida")
    })
    @PostMapping
    public ResponseEntity<APIResponse<Shipment>> createShipment(
            @RequestBody ShipmentDTO shipmentDTO
    ) {
        Shipment shipment = shipmentService.createShipment(shipmentDTO);
        APIResponse<Shipment> response = new APIResponse<>(
                "Shipment created successfully",
                HttpStatus.CREATED.value(),
                List.of(shipment)
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Deletar um envio por ID", description = "Apaga um envio específico baseado no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Envio deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Envio não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteShipment(@PathVariable UUID id) {
        shipmentService.deleteShipment(id);
        APIResponse<Void> response = new APIResponse<>(
                "Shipment deleted successfully",
                HttpStatus.NO_CONTENT.value()
        );
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
