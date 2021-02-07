package com.webclient.demo.service;

import com.webclient.demo.dto.Employee;
import com.webclient.demo.exception.NotAbleToDetchDataException;
import com.webclient.demo.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class WebClientService {

    private WebClient webClient;

    private static final String GET_ALL_EMPLOYEES="/allemployees";
    private static final String ADD_EMPLOYEE = "/employee";

    public WebClientService(WebClient webClient)
    {
        this.webClient=webClient;
    }

    //return type is String because (wire)mock api is returning String
    public String getAllEmployees()
    {
        return webClient.get().uri(GET_ALL_EMPLOYEES)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    //return type is String because (wire)mock api is returning String
    public String addEmployee(Employee employee)
    {
        return webClient.post().uri(ADD_EMPLOYEE)
                .bodyValue(employee)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    //return type is String because (wire)mock api is returning String
    public String getAllEmployeesWithCustomErrorHandling()
    {
        return webClient.get().uri(GET_ALL_EMPLOYEES)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,clientResponse -> handle4XXError(clientResponse))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> handle5XXError(clientResponse))
                .bodyToMono(String.class)
                .block();
    }

    private Mono<? extends Throwable> handle5XXError(ClientResponse clientResponse) {
        Mono<String> errorMessage=clientResponse.bodyToMono(String.class);
        return errorMessage.flatMap(message->{
            log.error("Error response ode is "+clientResponse.rawStatusCode()+" and the message is "+message);
            throw new ServiceException(message);
        });
    }

    private Mono<? extends Throwable> handle4XXError(ClientResponse clientResponse) {
        Mono<String> errorMessage=clientResponse.bodyToMono(String.class);
        return errorMessage.flatMap(message -> {
            log.error("Error response code is "+clientResponse.rawStatusCode()+ " and the message is "+message);
            throw new NotAbleToDetchDataException(message);
        });
    }
}
