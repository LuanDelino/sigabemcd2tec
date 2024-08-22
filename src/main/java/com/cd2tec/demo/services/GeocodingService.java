package com.cd2tec.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cd2tec.demo.exceptions.handler.ResourceBadRequestException;

@Service
public class GeocodingService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public GeocodingService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public double[] getCoordinates(String address) {
        String url = "https://nominatim.openstreetmap.org/search?q=" + address + "&format=json&limit=1";
        try {
            String response = restTemplate.getForObject(url, String.class);
            JsonNode jsonArray = objectMapper.readTree(response);

            if (jsonArray.isArray() && jsonArray.size() > 0) {
                JsonNode jsonObject = jsonArray.get(0);
                double lat = jsonObject.get("lat").asDouble();
                double lon = jsonObject.get("lon").asDouble();
                return new double[] { lat, lon };
            } else {
                throw new ResourceBadRequestException("Endereço não encontrado: " + address);
            }
        } catch (Exception e) {
            throw new ResourceBadRequestException("Erro ao consultar o endereço: " + address + ". " + e.getMessage());
        }
    }
}
