 import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Evolution } from '../models/evolution.model';

@Injectable({
  providedIn: 'root'
})
export class EvolutionService {
  private readonly apiUrl = 'http://localhost:8080/evolutions'

  constructor(private httpClient: HttpClient) { }

  addEvolution(body: string, pokemonPokedexNumber: number): Observable<Evolution> {
    const params = new HttpParams().set('pokemonPokedexNumber', pokemonPokedexNumber);
    return this.httpClient.post<Evolution>(this.apiUrl, body, { params });
  }

  getEvolutions(): Observable<Evolution[]> {
    return this.httpClient.get<Evolution[]>(this.apiUrl);
  }

  updateEvolution(evolution: Evolution) {
    return this.httpClient.put<Evolution>(this.apiUrl, evolution);
  }

  removeEvolution(pokedexNumber: number): Observable<Evolution> {
    return this.httpClient.delete<Evolution>(this.apiUrl + '/' + pokedexNumber);
  }

}
