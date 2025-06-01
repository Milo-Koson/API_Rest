package fr.eseo.twic.milokoson.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class ClientRest {

    private RestTemplate restTemplate;

    @Value("${api.baseurl}")

    public void YourRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ClientRest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

}