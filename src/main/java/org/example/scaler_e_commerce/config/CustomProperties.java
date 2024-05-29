package org.example.scaler_e_commerce.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom")
@Getter
@Setter
public class CustomProperties {

    private String userserviceUrl;
}
