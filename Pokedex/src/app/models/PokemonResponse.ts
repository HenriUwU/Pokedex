import { Pokemon } from "./pokemon.model";

export interface PokemonResponse {
	content: Pokemon[]
	pageNo: number;
	pageSize: number;
	totalElements: number;
	totalPages: number;
	last: boolean;
}