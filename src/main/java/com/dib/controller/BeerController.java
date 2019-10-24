package com.dib.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dib.controller.dto.BeerDTO;
import com.dib.controller.dto.system.ResponseWrapper;
import com.dib.exception.CustomNotFoundException;
import com.dib.service.BeerService;

@RestController
@RequestMapping("/api")
public class BeerController {
	
	@Autowired
	BeerService beerService;
	
	@RequestMapping(value = "/beers/fillUpBeers", method = RequestMethod.POST)
	ResponseEntity<?> fillUpBeers() throws KeyManagementException, IOException, GeneralSecurityException {
		String message = this.beerService.fillUpBeers();
		return new ResponseEntity<Object>(new ResponseWrapper(message), HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/beers", method = RequestMethod.GET)
	ResponseEntity<?> loadAll(){
		List<BeerDTO> res = this.beerService.loadAll();
		return new ResponseEntity<Object>(new ResponseWrapper(res), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/beers/{id}", method = RequestMethod.GET)
	ResponseEntity<?> findById(@PathVariable Integer id) throws Exception {
		
		BeerDTO res = this.beerService.findById(id);
		return new ResponseEntity<>(new ResponseWrapper(res), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/beers/{id}", method = RequestMethod.DELETE)
	ResponseEntity<?> delete(@PathVariable Integer id) throws Exception {
		if (id == null || !beerService.exists(id)) {
			throw new CustomNotFoundException("ID NOT FOUND: ID VALUE: " + id);
		}
		this.beerService.delete(id);
		return new ResponseEntity<>(new ResponseWrapper("BEER HAS BEEN DELETED"), HttpStatus.OK);
	}
	

}
