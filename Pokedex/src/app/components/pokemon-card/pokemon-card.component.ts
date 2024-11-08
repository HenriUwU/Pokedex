import { Component, NgModule, OnInit } from '@angular/core';
import { Pokemon } from '../../models/pokemon.model';
import { PokedexService } from '../../services/pokedex.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { PokemonResponse } from '../../models/PokemonResponse';

@Component({
  selector: 'app-pokemon-card',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './pokemon-card.component.html',
  styleUrl: './pokemon-card.component.scss'
})
export class PokemonCardComponent implements OnInit {
  pokemons: Pokemon[] = [];
  isEditing: { [key:number]: boolean } = {};
  pageNo = 0;
  pageSize = 4;
  totalPages = 0;
  searchTerm: String = '';

  constructor(private pokedexService: PokedexService,
              private router: Router
  ) {}

  ngOnInit(): void {
    this.loadPokemons();
  }

  removePokemon(pokedexNumber: number): void {
    this.pokedexService.removePokemon(pokedexNumber).subscribe(() => {
      this.loadPokemons();
    });
  }

  editPokemon(pokedexNumber: number): void {
    this.isEditing[pokedexNumber] = true;
  }

  updatePokemon(pokemon: Pokemon): void {
    this.pokedexService.updatePokemon(pokemon).subscribe(() =>
      this.isEditing[pokemon.pokedexNumber] = false
    );
  }

  cancelEdit(pokedexNumber: number): void {
    this.isEditing[pokedexNumber] = false;
  }

  loadPokemons(): void {
    this.pokedexService.getPokemons(this.pageNo, this.pageSize, this.searchTerm).subscribe(
      (response: PokemonResponse) => {
        this.pokemons = response.content;
        this.pageNo = response.pageNo;
        this.pageSize = response.pageSize;
        this.totalPages = response.totalPages;
      },
      error => {
        console.error('Error when trying to get pokemons', error);
      }
    )
  }

  searchPokemons(): void {
    this.pageNo = 0;
    this.loadPokemons();
  }

  goToPreviousPage(): void {
    this.pageNo--;
    this.loadPokemons();
  }

  goToNextPage(): void {
    this.pageNo++;
    this.loadPokemons();
  }

}
