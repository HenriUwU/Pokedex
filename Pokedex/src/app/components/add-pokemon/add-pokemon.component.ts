import { Component, OnInit } from '@angular/core';
import { FormGroup, FormsModule, FormBuilder} from '@angular/forms';
import { Router } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { PokedexService } from '../../services/pokedex.service';

@Component({
  selector: 'app-add-pokemon',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './add-pokemon.component.html',
  styleUrl: './add-pokemon.component.scss'
})
export class AddPokemonComponent implements OnInit {

	newPokemonForm!: FormGroup;

	constructor(private router: Router, 
		private formBuilder: FormBuilder, 
		private pokedexService: PokedexService) {}

	ngOnInit(): void {
		this.newPokemonForm = this.formBuilder.group({
			pokedexNumber: [null],
			name: [null],
			description: [null],
			imageUrl: [null],
			type: [null]
		});
	}

	onAddNewPokemon(): void {
		this.router.navigateByUrl('pokedex');
	}

	onSubmitForm(): void {
		this.pokedexService.addPokemon(this.newPokemonForm.value).subscribe();
	}

}
