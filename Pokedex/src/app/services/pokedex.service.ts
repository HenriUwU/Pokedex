import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pokemon } from '../models/pokemon.model';

@Injectable({
  providedIn: 'root'
})
export class PokedexService {
  private readonly apiUrl = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) { }

  getPokemons(): Observable<Pokemon[]> {
    return this.httpClient.get<Pokemon[]>(this.apiUrl + '/pokemons');
  }

  addPokemon(body: string): Observable<Pokemon> {
    return this.httpClient.post<Pokemon>(this.apiUrl + '/addPokemon', body);
  }

  removePokemon(pokedexNumber: number): Observable<Pokemon> {
    return this.httpClient.delete<Pokemon>(this.apiUrl + '/removePokemon/' + pokedexNumber.toString());
  }

  updatePokemon(pokedexNumber: number, pokemon: Pokemon): Observable<Pokemon> {
    return this.httpClient.put<Pokemon>(this.apiUrl + '/updatePokemon/' + pokedexNumber.toString(), pokemon);
  }
}
