package org.example.scaler_e_commerce.authenticationClient;

import org.example.scaler_e_commerce.authenticationClient.dtos.ValidateResponseDto;
import org.example.scaler_e_commerce.authenticationClient.dtos.ValidateTokenRequestDto;
import org.example.scaler_e_commerce.config.CustomProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationClient {

    private final RestTemplateBuilder restTemplateBuilder;

    private final CustomProperties customProperties;


    public AuthenticationClient(RestTemplateBuilder restTemplateBuilder, CustomProperties customProperties) {
        this.restTemplateBuilder = restTemplateBuilder;

        this.customProperties = customProperties;
    }


    public ValidateResponseDto validate(String token) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ValidateTokenRequestDto validateTokenRequestDto = new ValidateTokenRequestDto();
        validateTokenRequestDto.setToken(token);
        ResponseEntity<ValidateResponseDto> request = restTemplate.postForEntity(customProperties.getUserserviceUrl() + "/auth/validate", validateTokenRequestDto, ValidateResponseDto.class);
        return request.getBody();
    }
}
