package com.dib.wsclient;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import com.dib.wsclient.quest.RestResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLContext;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class PunkApiClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(PunkApiClient.class);
	private static final String URL_API = "https://api.punkapi.com/v2/beers/random";

	public List<RestResponse> sendRequestGetData() {

		List<RestResponse> responseValue = new ArrayList<>();
		TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
		SSLContext sslContext;

		try {
			sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
			SSLConnectionSocketFactory customSSLConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
					NoopHostnameVerifier.INSTANCE);

			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
					.register("https", customSSLConnectionSocketFactory)
					.register("http", new PlainConnectionSocketFactory()).build();

			BasicHttpClientConnectionManager connectionManager = new BasicHttpClientConnectionManager(
					socketFactoryRegistry);
			CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(customSSLConnectionSocketFactory)
					.setConnectionManager(connectionManager).build();
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
					httpClient);

			// initialization of custom RestTemplate Spring class
			RestTemplate restTemplate = new RestTemplate(requestFactory);
			ResponseEntity<String> response = restTemplate.getForEntity(URL_API, String.class);
			LOGGER.info("PUNK API WS SENDING REQUEST; URL: " + URL_API);

			if (response.getStatusCode() != HttpStatus.OK) {
				LOGGER.info("Error while getting the beer!");
				return responseValue;
			}

			ObjectMapper mapper = new ObjectMapper();

			try {
				responseValue = mapper.readValue(response.getBody().toString(),
						new TypeReference<List<RestResponse>>() {
						});
				LOGGER.info("PUNK API WS RESPONSE: NAME: " + responseValue.get(0).getName());
				LOGGER.info("PUNK API WS RESPONSE: DESCRIPTION: " + responseValue.get(0).getDescription());

			} catch (IOException e) {
				LOGGER.info("Failed while parsing json:" + e.getMessage());
				return responseValue;
			}

		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e1) {
			LOGGER.info("Couldn't create SSL Context!");
			return responseValue;
		}

		return responseValue;
	}
}
