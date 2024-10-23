import { Routes } from '@angular/router';
import { PokemonListComponent } from './components/pokemon-list/pokemon-list.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { AddPokemonComponent } from './components/add-pokemon/add-pokemon.component';

export const routes: Routes = [
	{ path: '', component: LandingPageComponent },
	{ path: 'pokedex', component: PokemonListComponent },
	{ path: 'addPokemon', component: AddPokemonComponent }
];
