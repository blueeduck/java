package org.jboss.eap.quickstarts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
public class QuickstartKitchensinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickstartKitchensinkApplication.class, args);
	}
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
	    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	    resolver.setPrefix("/static/");
	    resolver.setSuffix(".xhtml");  // If using JSF, set this appropriately
	    return resolver;
	}
}
