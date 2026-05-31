import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { PropertyService } from '../services/property.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  user: any = null;
  myProperties: any[] = [];
  loading = true;
  stats = {
    totalProperties: 0,
    featuredProperties: 0,
    availableProperties: 0
  };

  constructor(
    private authService: AuthService,
    private propertyService: PropertyService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.user = this.authService.getUser();
    this.loadMyProperties();
  }

  loadMyProperties(): void {
    this.propertyService.getMyProperties(0, 10).subscribe({
      next: (response) => {
        this.myProperties = response.content;
        this.calculateStats();
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading properties:', error);
        this.loading = false;
      }
    });
  }

  calculateStats(): void {
    this.stats.totalProperties = this.myProperties.length;
    this.stats.featuredProperties = this.myProperties.filter(p => p.featured).length;
    this.stats.availableProperties = this.myProperties.filter(p => p.available).length;
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/home']);
  }
}
