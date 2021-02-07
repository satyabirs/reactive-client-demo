package com.webclient.demo.service;

import com.webclient.demo.dto.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class WebClientServiceTest {

    private static final String baseUrl="http://localhost:8080/";
    private WebClient webClient=WebClient.create(baseUrl);

    WebClientService service=new WebClientService(webClient);

    @Test
    void testRetrieveAllEmployees()
    {
        String loe= service.getAllEmployees();
        assertTrue(loe != null);
    }

    @Test
    void testAddEmployee()
    {
        Employee employee=new Employee("001","alex","engineer");
        String employee1=service.addEmployee(employee);
        assertTrue(employee1!=null);
    }
}