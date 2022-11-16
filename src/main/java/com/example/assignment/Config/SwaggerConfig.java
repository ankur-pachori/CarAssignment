package com.example.assignment.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;


@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docketApi(ServletContext servletContext){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
//                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().pathProvider(new RelativePathProvider(servletContext) {
                    @Override
                    public String getApplicationBasePath() {
                        return "/myapi";
                    }
                });
//                .apiInfo(apiInfo());
//                .pathProvider(new RelativePathProvider(servletContext){
//                    @Override
//                    public String getApplicationBasePath(){
//                        return baseUrl;
//                    }
//                });
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("Car Api").description(
                "API to add, remove and delete car details").version("1")
                .license("Apache 2.0")
//                .licenseUrl("https://www.apache.org/liicenses")
                .build();
    }
}
