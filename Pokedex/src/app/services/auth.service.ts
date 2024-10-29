import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, map, Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
	private apiUrl = 'http://localhost:8080/auth';
	private currentUserSubject: BehaviorSubject<string | null>;
	public currentUser: Observable<string | null>;

	constructor(private http: HttpClient) {
		this.currentUserSubject = new BehaviorSubject<string | null>(localStorage.getItem('token'));
		this.currentUser = this.currentUserSubject.asObservable();
	}

	register(user: User): Observable<any> {
		return this.http.post(`${this.apiUrl}/register`, user);
	}

	login(user: User): Observable<any> {
		return this.http.post<{ token: string}>(`${this.apiUrl}/login`, user).pipe(
			map(response => {
				localStorage.setItem(`token`, response.token);
				this.currentUserSubject.next(response.token);
				return response;
			})
		);
	}

	isLoggedIn(): boolean {
		return !!localStorage.getItem(`token`);
	}

	getToken(): string | null {
		return localStorage.getItem(`token`);
	}

}
