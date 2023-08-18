//package com.gdu.nhom1.shopproject.swagger;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//
//@Configuration
//@EnableSwagger2
//public class SwaggerUi {
//    @Bean
//    public Docket swagerui(){
//        return new Docket(DocumentationType.SWAGGER_2).groupName("public-API")
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.gdu.nhom1.shopproject.controllers")) // Thay đổi gói dự án của bạn
//                //.paths(PathSelectors.any())
//                .build();
//                //.apiInfo(apiInfo());
//    }
//    private ApiInfo apiInfo(){
//        return new ApiInfoBuilder().title("shop dien tu")
//                .description("voi api danh cho khach hang, san pham va gio hang.")
//                .termsOfServiceUrl("https://google.com")
//                .licenseUrl("google@gmail.com").version("1.0").build();
//    }
//}
