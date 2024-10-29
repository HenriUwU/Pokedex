import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, map, Observable } from 'rxjs';
import { User } from '../models/user.model';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
	private apiUrl = 'http://localhost:8080/auth';
	private currentUserSubject: BehaviorSubject<string | null>;
	public currentUser: Observable<string | null>;

	constructor(private http: HttpClient) {
		this.currentUserSubject = new BehaviorSubject<string | null>(sessionStorage.getItem('token'));
		this.currentUser = this.currentUserSubject.asObservable();
	}

	register(user: User): Observable<any> {
		return this.http.post(`${this.apiUrl}/register`, user);
	}

	login(user: User): Observable<any> {
		return this.http.post<{ token: string}>(`${this.apiUrl}/login`, user).pipe(
			map(response => {
				sessionStorage.setItem(`token`, response.token);
				this.currentUserSubject.next(response.token);
				return response;
			})
		);
	}

	isLoggedIn(): boolean {
		return !!sessionStorage.getItem(`token`);
	}

	getToken(): string | null {
		return sessionStorage.getItem(`token`);
	}

	getUsernameFromToken(): string | null {
		const token = this.getToken();
		if (token) {
			const decoded: any = jwtDecode(token);
			return decoded.sub;
		}
		return null;
	}

}
