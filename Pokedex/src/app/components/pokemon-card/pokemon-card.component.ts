import { Component, NgModule, OnInit } from '@angular/core';
import { Pokemon } from '../../models/pokemon.model';
import { PokedexService } from '../../services/pokedex.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { EvolutionCardComponent } from '../evolution-card/evolution-card.component';

@Component({
  selector: 'app-pokemon-card',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './pokemon-card.component.html',
  styleUrl: './pokemon-card.component.scss'
})
export class PokemonCardComponent implements OnInit {
  pokemons!: Pokemon[];
  isEditing!: { [key:number]: boolean };

  constructor(private pokedexService: PokedexService,
              private router: Router
  ) {}

  ngOnInit(): void {
    this.pokedexService.getPokemons().subscribe(
      data => {
        this.pokemons = data;
      },
      error => {
        console.error('Error when trying to get pokemons', error);
      }
    )
    this.isEditing = {};
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
    this.pokedexService.getPokemons().subscribe(pokemons => {
      this.pokemons = pokemons;
    });
  }

}
