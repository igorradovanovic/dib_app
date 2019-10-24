package com.dib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.apache.catalina.startup.Tomcat;

@SpringBootApplication
@EnableCaching
public class DibAppApplication {
	
	@Value( "${spring.datasource.url}" )
	private  String jdbcUrl;

	@Value( "${spring.datasource.username}")
	private  String jdbcUsername;

	@Value( "${spring.datasource.password}")
	private  String jdbcPassword;

	public static void main(String[] args) {
		SpringApplication.run(DibAppApplication.class, args);
		
		
	}
	@Bean
	public TomcatServletWebServerFactory tomcatFactory() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {

			@Override
			protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
				tomcat.enableNaming();
				return super.getTomcatWebServer(tomcat);
			}

		};
		return tomcat;
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
