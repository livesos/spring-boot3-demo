package com.demo.springboot3demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lives@gamevector
 * @since 2024/6/10
 */
@Configuration
public class knife4jConfig {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder().group("api").pathsToMatch("/**").build();
    }

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot 3 Demo")
                        .description("spring boot 3 demo dd")
                        .version("1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                );
    }

}
