import { Component, OnInit } from '@angular/core';
import { Pokemon } from '../../models/pokemon.model';
import { PokedexService } from '../../services/pokedex.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-pokemon-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pokemon-card.component.html',
  styleUrl: './pokemon-card.component.scss'
})
export class PokemonCardComponent implements OnInit {
  pokemons!: Pokemon[];

  constructor(private pokedexService: PokedexService) {}

  ngOnInit(): void {
      this.pokedexService.getPokemons().subscribe(
        data => {
          this.pokemons = data;
          console.log('pokemons:', this.pokemons);
        },
        error => {
          console.error('Error when trying to get pokemons', error);
        }
      )
  }
}
