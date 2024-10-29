import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pokemon } from '../models/pokemon.model';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class PokedexService {
  private readonly apiUrl = 'http://localhost:8080/pokemons';

  constructor(private httpClient: HttpClient,
              private authService: AuthService
  ) { }

  getPokemons(): Observable<Pokemon[]> {
    return this.httpClient.get<Pokemon[]>(this.apiUrl);
  }

  addPokemon(body: string): Observable<Pokemon> {
    return this.httpClient.post<Pokemon>(this.apiUrl, body);
  }

  removePokemon(pokedexNumber: number): Observable<Pokemon> {
    return this.httpClient.delete<Pokemon>(this.apiUrl + '/' + pokedexNumber);
  }

  updatePokemon(pokemon: Pokemon): Observable<Pokemon> {
    return this.httpClient.put<Pokemon>(this.apiUrl, pokemon);
  }
}
