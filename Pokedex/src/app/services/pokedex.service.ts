import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pokemon } from '../models/pokemon.model';

@Injectable({
  providedIn: 'root'
})
export class PokedexService {
  private readonly apiUrl = 'http://localhost:8080';
  private readonly pokemonsEndpoint = '/pokemons';

  constructor(private httpClient: HttpClient) { }

  getPokemons(): Observable<Pokemon[]> {
    return this.httpClient.get<Pokemon[]>(this.apiUrl + this.pokemonsEndpoint);
  }
}
