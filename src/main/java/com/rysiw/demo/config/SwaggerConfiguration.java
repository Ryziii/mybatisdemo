package com.rysiw.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket desertApi(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rysiw.demo.controller"))
                .paths(PathSelectors.any())
                .build()
                .groupName("desertGroup")
                .enable(true);
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("swagger3文档")
                .description("文档描述信息")
                .contact(new Contact("1","1","1"))
                .version("1.0")
                .build();
    }
}
