package com.example.testRestAPI;

import com.example.testRestAPI.entity.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Component
public class Communication {

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;
    private final String url = "http://94.198.50.185:7081/api/users";

    public Communication(RestTemplate restTemplate, HttpHeaders headers) {
        this.restTemplate = restTemplate;
        this.headers = headers;
        this.headers.set("Cookie", String.join(";",
                Objects.requireNonNull(restTemplate.headForHeaders(url).get("Set-Cookie"))));
    }


    public List<User> getAllUsers(){

        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<User>>(){});
        return responseEntity.getBody();
    }

    public String getAnswer() {
        return addUser().getBody() + updateUser().getBody() + deleteUser().getBody();
    }

    public ResponseEntity<String> addUser(){
        User newUser =new User(3L, "James", "Brown",(byte) 25);
        HttpEntity<User> entity = new HttpEntity<>(newUser,headers);
        return restTemplate.postForEntity(url,entity, String.class);
    }

    public ResponseEntity<String> updateUser(){
        User upUser = new User(3L, "Thomas", "Shelby", (byte) 25);
        HttpEntity<User> entity = new HttpEntity<>(upUser,headers);
        return restTemplate.exchange(url,HttpMethod.PUT,entity,String.class,3L);
    }

    public ResponseEntity<String> deleteUser(){
        HttpEntity<User> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url +"/{id}",HttpMethod.DELETE,entity, String.class,3L);
    }
}
