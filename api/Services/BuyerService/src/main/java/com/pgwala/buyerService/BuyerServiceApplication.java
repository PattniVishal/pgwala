package com.pgwala.buyerService;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class BuyerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuyerServiceApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public Docket swaggerConfig() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.loginService"))
				.build()
				.apiInfo(apiDetails());
	}
	
	@SuppressWarnings("deprecation")
	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Buyer Service", 
				"buyer related services", 
				"1.0", 
				"Free to use", 
				"Vishal", 
				"API License", 
				"vishalpattnidotcom");
	}

}
