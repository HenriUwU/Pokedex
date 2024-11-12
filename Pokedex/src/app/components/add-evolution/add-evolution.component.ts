import { Component, NgModule, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, NgModel, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { EvolutionService } from '../../services/evolution.service';
import { Pokemon } from '../../models/pokemon.model';
import { PokedexService } from '../../services/pokedex.service';
import { NgForOf } from '@angular/common';
import { PokemonResponse } from '../../models/PokemonResponse';

@Component({
  selector: 'app-add-evolution',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, RouterLink, NgForOf],
  templateUrl: './add-evolution.component.html',
  styleUrl: './add-evolution.component.scss'
})
export class AddEvolutionComponent implements OnInit {
  newEvolutionForm!: FormGroup;
  pokemonPokedexNumber!: number;
  pokemons!: Pokemon[];
  searchTerm!: string;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private evolutionService: EvolutionService,
    private pokedexService: PokedexService
  ) { }

  ngOnInit(): void {
    this.newEvolutionForm = this.formBuilder.group({
        pokedexNumber: ['', Validators.required],
        name: ['', Validators.required],
        description: ['', Validators.required],
        imageUrl: ['', Validators.required]
    })

    this.pokedexService.getPokemons(0, 100).subscribe(
      (response: PokemonResponse) =>
        this.pokemons = response.content
    )
  }

  onSubmitForm(): void {
    if (this.newEvolutionForm.valid) {
      this.evolutionService.addEvolution(this.newEvolutionForm.value, this.pokemonPokedexNumber).subscribe(() =>
        this.router.navigateByUrl('pokedex')  
      )
    }
  }

  onPokemonSelect(event: Event) {
    const target = event.target as HTMLSelectElement;
    this.pokemonPokedexNumber = Number(target.value);
  }

  searchEvolution(): void {
    this.evolutionService.addEvolutionFromAPI(this.searchTerm, this.pokemonPokedexNumber).subscribe(() =>
      this.router.navigateByUrl('pokedex')
    );
  }

}
