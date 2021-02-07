package com.webclient.demo.controller;

import com.webclient.demo.dto.Employee;
import com.webclient.demo.service.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RequestMapping("/v1")
@RestController
public class WebClientController {

    @Autowired
    private WebClient webClient;

    @Autowired
    private WebClientService webClientService;

    @GetMapping("/allemployes")
    public String getAllEmployees()
    {
        return webClientService.getAllEmployees();
    }

    @PostMapping("/employee")
    public String addEmployee(@RequestBody Employee employee)
    {
        return webClientService.addEmployee(employee);
    }

}
