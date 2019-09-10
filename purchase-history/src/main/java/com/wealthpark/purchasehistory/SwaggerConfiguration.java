package com.wealthpark.purchasehistory;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
	
	@Bean
	public Docket inquiryAndComplaintApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.wealthpark")).build()
				.apiInfo(metaInfo());
	}
	
	private ApiInfo metaInfo() {
		@SuppressWarnings("deprecation")
		ApiInfo apiInfo= new ApiInfo("ProductHistory Backend API", 
				"This application is collection of Rest APIs. Presently it contains end points to register customer, product, purchase product and purchase history.",
				"backend_API",
				"pallav.raj14@gmail.com",	
				"PurchaseHistory Backend API",
				"PurchaseHistry application",
				"https://github.com/PallavRaj14/");
				return apiInfo;
	}
	
}
