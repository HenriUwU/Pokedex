import { Component, OnInit } from '@angular/core';
import { Pokemon } from '../../models/pokemon.model';
import { PokedexService } from '../../services/pokedex.service';

@Component({
  selector: 'app-pokedex',
  standalone: true,
  imports: [],
  templateUrl: './pokedex.component.html',
  styleUrl: './pokedex.component.scss'
})
export class PokedexComponent implements OnInit {

  pokemons!: Pokemon[];

  constructor(private pokedexService: PokedexService) {}

  ngOnInit(): void {
      this.pokedexService.getPokemons().subscribe(
        data => {
          this.pokemons = data;
          console.log('pokemons:', this.pokemons);
        },
        error => {
          console.error('Erro while trying to get pokemons', error);
        }
      )
  }
}
