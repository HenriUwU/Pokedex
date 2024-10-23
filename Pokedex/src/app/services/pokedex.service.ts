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
  private readonly body = "{\"pokedexNumber\":1,\"name\":\"Bulbasaur\",\"description\":\"The notorious\",\"type\":\"ELECTRIC\",\"imageUrl\":\"https://static.vecteezy.com/system/resources/previews/024/804/557/non_2x/pikachu-art-or-illustration-on-pickachu-free-vector.jpg\"}";

  constructor(private httpClient: HttpClient) { }

  getPokemons(): Observable<Pokemon[]> {
    return this.httpClient.get<Pokemon[]>(this.apiUrl + this.pokemonsEndpoint);
  }
}
