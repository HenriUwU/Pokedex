import { Routes } from '@angular/router';
import { PokemonListComponent } from './components/pokemon-list/pokemon-list.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { AddPokemonComponent } from './components/add-pokemon/add-pokemon.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { authGuard } from './auth.guard';
import { AddEvolutionComponent } from './components/add-evolution/add-evolution.component';

export const routes: Routes = [
	{ path: '', component: LandingPageComponent, canActivate: [authGuard] },
	{ path: 'pokedex', component: PokemonListComponent, canActivate: [authGuard] },
	{ path: 'addPokemon', component: AddPokemonComponent, canActivate: [authGuard] },
	{ path: 'register', component: RegisterComponent },
	{ path: 'login', component: LoginComponent },
	{ path: 'addEvolution', component: AddEvolutionComponent, canActivate: [authGuard] }
];
