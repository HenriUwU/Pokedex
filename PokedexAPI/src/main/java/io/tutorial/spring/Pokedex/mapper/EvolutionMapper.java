package io.tutorial.spring.Pokedex.mapper;

import io.tutorial.spring.Pokedex.dto.EvolutionDTO;
import io.tutorial.spring.Pokedex.model.Evolution;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EvolutionMapper {

    EvolutionDTO toDto(Evolution evolution);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "pokemon", ignore = true)
    @Mapping(target = "user", ignore = true)
    Evolution toEntity(EvolutionDTO evolutionDTO);

    List<EvolutionDTO> toDtoList(Iterable<Evolution> evolutions);

}
