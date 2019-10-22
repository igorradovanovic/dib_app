package com.dib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.dib.sys.CustomRepositoryImpl;

import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class)
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

			@Override
			protected void postProcessContext(Context context) {
				ContextResource resource = new ContextResource();
				resource.setName("jdbc/Dib");
				resource.setType(DataSource.class.getName());
				resource.setProperty("username", jdbcUsername);
				resource.setProperty("password", jdbcPassword);
				resource.setProperty("url", jdbcUrl);
				resource.setProperty("maxTotal", "40");
				resource.setAuth("Container");
				
				context.getNamingResources().addResource(resource);
			}
		};
		return tomcat;
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
