package com.dib.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dib.controller.dto.system.ResponseWrapper;
import com.dib.wsclient.PunkApiClient;
import com.dib.wsclient.quest.RestResponse;
import com.dib.config.WebServiceConfig;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	WebServiceConfig webServiceConfig;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<?> loadAll() throws KeyManagementException, IOException, GeneralSecurityException {
		PunkApiClient punkApiClient = webServiceConfig.initializePunkApiClient();
		List<RestResponse> res1 = punkApiClient.sendRequestGetData();
		return new ResponseEntity(new ResponseWrapper(res1), HttpStatus.OK); 
	}

}
