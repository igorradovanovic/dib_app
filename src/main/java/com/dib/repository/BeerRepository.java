package com.dib.repository;

import org.springframework.stereotype.Repository;
import com.dib.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Integer> {

	boolean existsByBerExtId(Integer berExtId);
}
