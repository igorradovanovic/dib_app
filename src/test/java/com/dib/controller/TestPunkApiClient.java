//package com.dib.controller;
//
//import java.io.IOException;
//import java.security.GeneralSecurityException;
//import java.security.KeyManagementException;
//import java.util.List;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.dib.config.WebServiceConfig;
//import com.dib.controller.dto.system.ResponseWrapper;
//import com.dib.wsclient.PunkApiClient;
//import com.dib.wsclient.quest.RestResponse;
//
//
//public class TestPunkApiClient {
//	
////	@Autowired
////	WebServiceConfig webServiceConfig;
////	
////	@DisplayName("punkApiClient.sendRequestGetData()")
////	@Test
////	public ResponseEntity<?> loadAll() throws KeyManagementException, IOException, GeneralSecurityException {
////		PunkApiClient punkApiClient = webServiceConfig.initializePunkApiClient();
////		List<RestResponse> res1 = punkApiClient.sendRequestGetData();
////		return new ResponseEntity(new ResponseWrapper(res1), HttpStatus.OK); 
////	}
////}
