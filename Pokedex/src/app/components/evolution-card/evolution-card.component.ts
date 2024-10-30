import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Evolution } from '../../models/evolution.model';
import { EvolutionService } from '../../services/evolution.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-evolution-card',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './evolution-card.component.html',
  styleUrl: './evolution-card.component.scss'
})
export class EvolutionCardComponent implements OnInit {
  evolutions!: Evolution[];
  isEditing!: { [key:number]: boolean };

  constructor(private evolutionService: EvolutionService,
              private router: Router
  ) {}

  ngOnInit(): void {
    this.evolutionService.getEvolutions().subscribe(
      data => {
        this.evolutions = data;
      },
      error => {
        console.error('Error when trying to get evolutions', error);
      }
    )
    this.isEditing = {};
  }

  removeEvolution(pokedexNumber: number): void {
    this.evolutionService.removeEvolution(pokedexNumber).subscribe(() => {
      this.loadEvolutions();
    });
  }

  editEvolution(pokedexNumber: number): void {
    this.isEditing[pokedexNumber] = true;
  }

  updateEvolution(evolution: Evolution): void {
    this.evolutionService.updateEvolution(evolution, ).subscribe(() =>
      this.isEditing[evolution.pokedexNumber] = false
    );
  }

  cancelEdit(pokedexNumber: number): void {
    this.isEditing[pokedexNumber] = false;
  }

  loadEvolutions(): void {
    this.evolutionService.getEvolutions().subscribe(evolutions => {
      this.evolutions = evolutions;
    });
  }
}
