package com.assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SPRING_WEB)
                .apiInfo(new ApiInfoBuilder()
                        .title("Shopping Application API")
                        .version("0.1")
                        .build())
                .groupName("Core")
                .globalOperationParameters(Arrays.asList(
                        this.authorizationHeader()))
                .directModelSubstitute(LocalDateTime.class, String.class)
                .directModelSubstitute(LocalDate.class, String.class)
                .directModelSubstitute(LocalTime.class, String.class);
    }


    private Parameter authorizationHeader() {
        return new ParameterBuilder()
                .description("Access Token")
                .parameterType("header")
                .name("Authorization")
                .modelRef(new ModelRef("string"))
                .required(false)
                .allowEmptyValue(false)
                .scalarExample("Bearer access_token")
                .build();
    }


    @Bean
    public UiConfiguration uiConfiguration() {
        return UiConfigurationBuilder.builder()
                .filter(true)
                .operationsSorter(OperationsSorter.METHOD)
                .build();
    }
}