//package com.p2h.notes.config;
//
//import io.swagger.models.auth.In;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.builders.RequestParameterBuilder;
//import springfox.documentation.schema.ScalarType;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger.web.*;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.Collections;
//import java.util.List;
//
//@EnableSwagger2
//@Configuration
//public class SwaggerConfiguration {
//
//    @Bean
//    public Docket mainApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .ignoredParameterTypes(AuthenticationPrincipal.class)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.p2h.notes.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .groupName("notes-application-api")
//                .apiInfo(apiInfo())
//                .useDefaultResponseMessages(false)
//                .securitySchemes(Collections.singletonList(securityScheme()))
//                .securityContexts(Collections.singletonList(securityContext()));
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Notes application API")
//                .description("API for managing notes application")
//                .version("1.0.0")
//                .build();
//    }
//
//    private SecurityScheme securityScheme() {
//        return new ApiKey("registrationToken", HttpHeaders.AUTHORIZATION, In.HEADER.name());
//    }
//
//    private SecurityContext securityContext() {
//        SecurityReference securityReference = SecurityReference.builder()
//                .reference("registrationToken")
//                .scopes(new AuthorizationScope[]{})
//                .build();
//
//        return SecurityContext.builder()
//                .securityReferences(Collections.singletonList(securityReference))
//                .build();
//    }
//
//    @Bean
//    public UiConfiguration uiConfig() {
//        return UiConfigurationBuilder.builder()
//                .deepLinking(true)
//                .displayOperationId(false)
//                .defaultModelsExpandDepth(1)
//                .defaultModelExpandDepth(1)
//                .defaultModelRendering(ModelRendering.EXAMPLE)
//                .displayRequestDuration(false)
//                .docExpansion(DocExpansion.LIST)
//                .filter(false)
//                .maxDisplayedTags(null)
//                .operationsSorter(OperationsSorter.METHOD)
//                .showExtensions(false)
//                .tagsSorter(TagsSorter.ALPHA)
//                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
//                .validatorUrl(null)
//                .build();
//    }
//}
//
