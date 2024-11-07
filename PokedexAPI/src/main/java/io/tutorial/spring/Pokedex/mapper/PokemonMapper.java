package io.tutorial.spring.Pokedex.mapper;

import io.tutorial.spring.Pokedex.dto.PokemonDTO;
import io.tutorial.spring.Pokedex.model.Pokemon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PokemonMapper {

    PokemonDTO toDto(Pokemon pokemon);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "user", ignore = true)
    Pokemon toPokemon(PokemonDTO pokemonDTO);

    List<PokemonDTO> toDtoList(Iterable<Pokemon> pokemons);

}
