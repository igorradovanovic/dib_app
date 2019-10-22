package com.dib.wsclient;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.dib.wsclient.quest.RestResponse;
import com.dib.wsclient.quest.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

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
	private static final String url = "https://api.punkapi.com/v2/beers/random";
	
	public String sendRequest() {
		
		final ClientHttpRequestFactory clientHttpRequestFactory = new CustomClientHttpRequestFactory();
		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        
        // Retrieve POJO 
        ResponseEntity<List<RestResponse>> response = restTemplate.exchange(url, HttpMethod.GET,entity, new ParameterizedTypeReference<List<RestResponse>>(){});
        List<RestResponse> responseList = response.getBody();
        
        // Retrieve JSON
  //    ResponseEntity<String> jsonResponse = restTemplate.exchage(url, HttpMethod.GET,entity, String.class);
  //    String jsonResponse = response.getBody();

		
		return "OK";
	}
}
