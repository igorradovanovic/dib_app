package com.dib.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.dib.controller.dto.BeerDTO;
import com.dib.model.Beer;

@Mapper(componentModel = "spring")
public interface BeerMapper {
	
	BeerDTO entityToDTO(Beer account);
	Beer dtoToEntity(BeerDTO accountDTO);
    
    List<BeerDTO> enitiesToDtos(List<Beer> list);
    List<Beer> dtoToEntities (List<BeerDTO> list);
    Beer updateEntityFromDto(BeerDTO dto, @MappingTarget Beer entity);
}
