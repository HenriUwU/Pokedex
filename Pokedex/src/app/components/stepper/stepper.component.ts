import { Component, OnInit, ViewChild } from '@angular/core';
import { MatStepper, MatStepperModule } from '@angular/material/stepper';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { PokedexService } from '../../services/pokedex.service';
import { EvolutionService } from '../../services/evolution.service';

@Component({
  selector: 'app-stepper',
  standalone: true,
  imports: [MatStepperModule, ReactiveFormsModule, FormsModule, RouterLink],
  templateUrl: './stepper.component.html',
  styleUrl: './stepper.component.scss'
})
export class StepperComponent implements OnInit {
  
  @ViewChild('stepper') stepper!: MatStepper;
  pokemonForm!: FormGroup;
  evolutionForm!: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private pokedexService: PokedexService,
              private evolutionService: EvolutionService,
              private router: Router
  ) { }

  ngOnInit(): void {
    this.pokemonForm = this.formBuilder.group({
      pokedexNumber: ['', Validators.required],
      name: ['', Validators.required],
      description: ['', Validators.required],
      imageUrl: ['', Validators.required]
    });

    this.evolutionForm = this.formBuilder.group({
      pokedexNumber: ['', Validators.required],
      name: ['', Validators.required],
      description: ['', Validators.required],
      imageUrl: ['', Validators.required]
    });
  }

  submitStepper(): void {
    this.pokedexService.addPokemon(this.pokemonForm.value).subscribe(() => {
      let pokedexNumber = this.pokemonForm.get('pokedexNumber')?.value;
      this.evolutionService.addEvolution(this.evolutionForm.value, pokedexNumber).subscribe(() => {
        this.router.navigateByUrl('pokedex');
      });
    });
  }

}
