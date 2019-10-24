package com.dib.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.util.List;
import com.dib.controller.dto.BeerDTO;
import com.dib.exception.CustomNotFoundException;
import com.dib.model.Beer;
import com.dib.wsclient.quest.Fermentation;
import com.dib.wsclient.quest.MashTemp;
import com.dib.wsclient.quest.RestResponse;

public interface BeerService {

	public List<BeerDTO> loadAll();

	public Integer create(BeerDTO input);

	public void update(BeerDTO input) throws Exception;

	public void delete(Integer id);

	public BeerDTO findById(Integer id) throws CustomNotFoundException;

	public boolean exists(Integer id);

	public String fillUpBeers() throws KeyManagementException, IOException, GeneralSecurityException;

	public List<Beer> getBeersFromPunkApi() throws KeyManagementException, IOException, GeneralSecurityException;

	public List<Beer> convertToBeerPojo(List<RestResponse> restResponse);

	public Float meanValue(List<MashTemp> mashTemp, Fermentation fermentation);
}
