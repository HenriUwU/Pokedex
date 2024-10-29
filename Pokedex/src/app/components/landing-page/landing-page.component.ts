import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-landing-page',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.scss'
})
export class LandingPageComponent implements OnInit {
  username: string | null = null;

  constructor(private authService: AuthService,
              private router: Router
  ) {}

  ngOnInit(): void {
      this.username = this.authService.getUsernameFromToken();
  }

  logout(): void {
    this.authService.logout();
    this.router.navigateByUrl('login');
  }
}
