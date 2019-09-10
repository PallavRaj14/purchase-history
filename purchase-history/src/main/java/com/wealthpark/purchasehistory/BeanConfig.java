package com.wealthpark.purchasehistory;

import org.dozer.DozerBeanMapper;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

	@Bean
	public DozerBeanMapper mapper() {
		return new DozerBeanMapper();
	}
	
	@Bean
	public WebServerFactoryCustomizer<TomcatServletWebServerFactory> 
	    containerCustomizer(){
	    return new EmbeddedTomcatCustomizer();
	}

	private static class EmbeddedTomcatCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

	    @Override
	    public void customize(TomcatServletWebServerFactory factory) {
	        factory.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
	            connector.setAttribute("relaxedPathChars", "<>[\\]^`{|}");
	            connector.setAttribute("relaxedQueryChars", "<>[\\]^`{|}");
	        });
	    }
	}
}
