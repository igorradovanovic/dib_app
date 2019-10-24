package com.dib.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.dib.config.WebServiceConfig;
import com.dib.controller.dto.BeerDTO;
import com.dib.exception.CustomNotFoundException;
import com.dib.mapper.BeerMapper;
import com.dib.model.Beer;
import com.dib.repository.BeerRepository;
import com.dib.wsclient.PunkApiClient;
import com.dib.wsclient.quest.Fermentation;
import com.dib.wsclient.quest.MashTemp;
import com.dib.wsclient.quest.RestResponse;

@Service // MILAN ... servisni sloj najvise treba da ima interfejse ... ako ti nije tesko izvuci ga u interfejs, a ovo zovi BeerServiceImpl
public class BeerService {

	@Autowired
	WebServiceConfig webServiceConfig;

	@Autowired
	private BeerRepository beerRepository;

	@Autowired
	private BeerMapper beerMapper;

	@Autowired
	RestTemplate restTemplate;

	final static int BEER_CAPACITY = 10;

	private static final Logger LOGGER = LoggerFactory.getLogger(BeerService.class);

	@Transactional(readOnly = true)
	public List<BeerDTO> loadAll() { // MILAN izbegavaj ovakve naizve promenljivih ... i dalje si ostavio ber. Nemoj res i res1 ... sta ti fali da zoves all beers i allbeerDtos
		List<Beer> allBeers = this.beerRepository.findAll();
		return this.beerMapper.enitiesToDtos(allBeers);
		// MILAN mozes i ostale metode ovde ovako da promenis i skratis
	}

	@Transactional
	public Integer create(BeerDTO input) {
		Beer entity = beerMapper.dtoToEntity(input);
		Beer ber = beerRepository.save(entity);
		return ber.getBerId();
	}

	@Transactional
	public void update(BeerDTO input) throws Exception {
		Beer beerDB = this.beerRepository.findById(input.getBerId()).orElse(null);
		if (input.getBerId() == null || beerDB == null) {
			throw new CustomNotFoundException("NOT FOUND");
		}

		beerMapper.updateEntityFromDto(input, beerDB);
		this.beerRepository.save(beerDB);
	}

	@Transactional
	public void delete(Integer id) {
		this.beerRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public BeerDTO findById(Integer id) throws Exception {
		if (id == null) {
			throw new CustomNotFoundException("NOT FOUND");
		}
		Optional<Beer> opt = this.beerRepository.findById(id);
		Beer ber = opt.orElseThrow(() -> new CustomNotFoundException("NOT FOUND"));
		BeerDTO BeerDTO = this.beerMapper.entityToDTO(ber);
		return BeerDTO;
	}

	@Transactional(readOnly = true)
	public boolean exists(Integer id) {
		return this.beerRepository.existsById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public String fillUpBeers() throws KeyManagementException, IOException, GeneralSecurityException {

		int numberOfStoredBeers = beerRepository.findAll().size();
		if (numberOfStoredBeers == BEER_CAPACITY) {
			return "BEER TABLE IS ALREADY FILLED UP TO MAXIMUM " + BEER_CAPACITY + " BEERS";
		}
		while (numberOfStoredBeers < BEER_CAPACITY) {
			List<Beer> beers = this.getBeersFromPunkApi();
			if (beers != null && !beers.isEmpty()) {
				for (Beer beer : beers) {
					if (!this.beerRepository.existsByBerExtId(beer.getBerId())) {
						this.beerRepository.save(beer);
						if (++numberOfStoredBeers == BEER_CAPACITY) {
							break;
						}
					} else {
						LOGGER.info("BEER ALREADY EXISTS; BEER_NAME: " + beer.getBerName());
					}
				}
			} else {
				LOGGER.warn("PUNK API BEER STORAGE REST RESPONSE IS NULL OR EMPTY!!!");
				break; // MILAN da ne bi upao u beskonacnu petlju ako je resposne null ili prazan
			}
		}
		return "BEER TABLE HAS BEEN FILLED UP TO MAXIMUM " + BEER_CAPACITY + " BEERS";
	}

	public List<Beer> getBeersFromPunkApi() throws KeyManagementException, IOException, GeneralSecurityException {
		List<Beer> beers = new ArrayList<>();
		PunkApiClient punkApiClient = webServiceConfig.initializePunkApiClient();
		List<RestResponse> restResponse = punkApiClient.sendRequestGetData();
		beers = this.convertToBeerPojo(restResponse);

		return beers;
	}

	public List<Beer> convertToBeerPojo(List<RestResponse> restResponse) {

		List<Beer> beers = new ArrayList<>();

		if (restResponse != null && !restResponse.isEmpty()) {
			for (RestResponse response : restResponse) {

				Beer beer = new Beer();
				beer.setBerName(response.getName() != null ? response.getName() : null);
				beer.setBerExtId(response.getId() != null ? response.getId().intValue() : null);
				beer.setBerDescription(response.getDescription() != null ? response.getDescription() : null);
				beer.setBerTemp(
						response.getMethod().getMashTemp() != null || response.getMethod().getFermentation() != null
								? this.meanValue(response.getMethod().getMashTemp(),
										response.getMethod().getFermentation())
								: null);
				beers.add(beer);
			}
		}

		return beers;
	}
	
	public Float meanValue(List<MashTemp> mashTemp, Fermentation fermentation) {
		long sum = 0;
		float result;

		if (mashTemp != null && !mashTemp.isEmpty()) {
			for (MashTemp one : mashTemp) {
				if (one.getTemp() != null && one.getTemp().getValue() != null) {
					sum += one.getTemp().getValue();
				}

			}
		}
		// MILAN nisam siguran da li i ovu temperaturu treba da racunas, ali mozda i da ... ostavi ovako
		if (fermentation.getTemp() != null) {
			if (fermentation.getTemp().getValue() != null) {
				sum += fermentation.getTemp().getValue();
			}

		}
		if (fermentation.getTemp() != null) {
			result = sum / mashTemp.size() + 1;
		} else {
			result = sum / mashTemp.size();
		}

		return result;

	}
}
