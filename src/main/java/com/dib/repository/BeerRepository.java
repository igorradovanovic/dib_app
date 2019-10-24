package com.dib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dib.model.Beer;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Integer> {

	boolean existsByBerExtId(Integer berExtId);
}
