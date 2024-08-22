package com.cd2tec.demo.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cd2tec.demo.model.APIResponse;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/healthcheck")
public class Healthcheck {

    @GetMapping()
    public APIResponse<Object> healthCheck() {
        return new APIResponse<>("Requisição feita com sucesso!", 200);
    }

}
