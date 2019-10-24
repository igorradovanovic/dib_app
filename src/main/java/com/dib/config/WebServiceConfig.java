package com.dib.config;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.dib.wsclient.PunkApiClient;


@Configuration
public class WebServiceConfig {
	
	private final Logger LOGGER = LoggerFactory.getLogger(WebServiceConfig.class);

	// https://api.punkapi.com/v2/beers/random
	@Bean
	public PunkApiClient initializePunkApiClient() throws IOException, KeyManagementException, GeneralSecurityException {
		
		PunkApiClient service = new PunkApiClient();
		LOGGER.info("PUNK API CLIENT INSTANCE HAS BEEN CREATED");
		return service;
	}
}
