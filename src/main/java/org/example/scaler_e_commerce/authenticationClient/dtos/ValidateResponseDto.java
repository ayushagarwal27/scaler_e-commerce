package org.example.scaler_e_commerce.authenticationClient.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateResponseDto {
    private UserDto userDto;
    private SessionStatus sessionStatus;
}
