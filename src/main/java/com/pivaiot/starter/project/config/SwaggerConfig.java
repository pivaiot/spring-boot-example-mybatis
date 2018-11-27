package com.pivaiot.starter.project.config;

import com.pivaiot.starter.project.StarterProjectApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiDocket() {

        // 添加自定义字段
        ParameterBuilder builder = new ParameterBuilder();
        builder.name("Access-Token").description("access token")
            .modelRef(new ModelRef("string"))
            .parameterType("header")
            .required(false)
            .build();

        List<Parameter> parameters = new ArrayList<>();
        parameters.add(builder.build());


        return new Docket(DocumentationType.SWAGGER_2)
            //.useDefaultResponseMessages(false)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage(StarterProjectApplication.class.getPackage().getName() + ".web.controller"))
            .paths(PathSelectors.any())
            .build()
            .globalOperationParameters(parameters);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("RESTful API")
            .description("RESTful API Docs")
            .version("1.0")
            .build();
    }
}
