package com.dib.repository;

import org.springframework.stereotype.Repository;
import com.dib.model.Beer;
import com.dib.sys.CustomRepository;

@Repository
public interface BeerRepository extends CustomRepository<Beer, Integer> {

	boolean existsByBerExtId(Integer berExtId);
}
