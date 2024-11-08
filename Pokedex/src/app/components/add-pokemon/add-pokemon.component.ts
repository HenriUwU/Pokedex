import { Component, OnInit } from '@angular/core';
import { FormGroup, FormsModule, FormBuilder, Validators} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { PokedexService } from '../../services/pokedex.service';

@Component({
  selector: 'app-add-pokemon',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './add-pokemon.component.html',
  styleUrl: './add-pokemon.component.scss'
})
export class AddPokemonComponent implements OnInit {

	newPokemonForm!: FormGroup;
	searchTerm!: String;

	constructor(private router: Router, 
		private formBuilder: FormBuilder, 
		private pokedexService: PokedexService
	) {	}

	ngOnInit(): void {
		this.newPokemonForm = this.formBuilder.group({
			pokedexNumber: ['', Validators.required],
			name: ['', Validators.required],
			description: ['', Validators.required],
			imageUrl: ['', Validators.required]
		});
		this.searchTerm = '';
	}

	onSubmitForm(): void {
		if (this.newPokemonForm.valid) {
			this.pokedexService.addPokemon(this.newPokemonForm.value).subscribe(() =>
				this.router.navigateByUrl('pokedex')
			);
		}
	}

	searchPokemon(): void {
		console.log('Searching Pokemon with name: ' + this.searchTerm);
		this.pokedexService.addPokemonFromAPI(this.searchTerm).subscribe(() =>
			this.router.navigateByUrl('pokedex')
		);
	}

}
