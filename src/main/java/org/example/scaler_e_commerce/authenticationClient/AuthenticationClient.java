package org.example.scaler_e_commerce.authenticationClient;

import org.example.scaler_e_commerce.authenticationClient.dtos.ValidateResponseDto;
import org.example.scaler_e_commerce.authenticationClient.dtos.ValidateTokenRequestDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationClient {

    private final RestTemplateBuilder restTemplateBuilder;

    public AuthenticationClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;

    }


    public ValidateResponseDto validate(String token) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ValidateTokenRequestDto validateTokenRequestDto = new ValidateTokenRequestDto();
        validateTokenRequestDto.setToken(token);
        ResponseEntity<ValidateResponseDto> request = restTemplate.postForEntity("http://localhost:9000/auth/validate", validateTokenRequestDto, ValidateResponseDto.class);
        return request.getBody();
    }
}
