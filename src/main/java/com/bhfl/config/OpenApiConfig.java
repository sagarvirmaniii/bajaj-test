package com.bhfl.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI bfhlOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("BFHL Chitkara Qualifier API")
                        .version("1.0.0")
                        .description("Production-ready REST API for the Chitkara Qualifier 1 BFHL problem."));
    }
}
