package io.tutorial.spring.Pokedex.mapper;

import io.tutorial.spring.Pokedex.dto.PokemonDTO;
import io.tutorial.spring.Pokedex.model.Pokemon;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PokemonMapper {

    PokemonMapper INSTANCE = Mappers.getMapper(PokemonMapper.class);

    PokemonDTO toDto(Pokemon pokemon);
    Pokemon toPokemon(PokemonDTO pokemonDTO);

    List<PokemonDTO> toDtoList(Iterable<Pokemon> pokemons);
    List<Pokemon> toPokemonList(Iterable<PokemonDTO> pokemonDTOs);

}
