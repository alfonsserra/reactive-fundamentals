package com.systelab.reactive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2WebFlux
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.systelab.reactive.controller"))
                .paths(regex("/reactive/v1.*"))
                .build()
                .apiInfo(metaData());
    }


    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Seed application programming interface (API)",
                "Restful API to manage the Seed Application to be used as an example.",
                "V1.0",
                "Terms of service",
                new Contact("Systelab", "https://github.com/systelab/", "systelab@werfen.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());
        return apiInfo;
    }

}