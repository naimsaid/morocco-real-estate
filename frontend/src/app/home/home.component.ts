import { Component, OnInit } from '@angular/core';
import { PropertyService } from '../services/property.service';
import { AuthService } from '../services/auth.service';
import { Property, PropertyType, ListingType } from '../models/property.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  featuredProperties: Property[] = [];
  recentProperties: Property[] = [];
  loading = true;

  searchFilters = {
    propertyType: '',
    listingType: '',
    city: ''
  };

  propertyTypes = Object.values(PropertyType);
  listingTypes = Object.values(ListingType);
  cities = ['Casablanca', 'Rabat', 'Marrakech', 'Fès', 'Tanger', 'Agadir'];

  constructor(private propertyService: PropertyService, public authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.loadProperties();
  }

  loadProperties(): void {
    this.propertyService.getFeaturedProperties(0, 6).subscribe({
      next: (response) => {
        this.featuredProperties = response.content;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading featured properties:', error);
        this.loading = false;
      }
    });

    this.propertyService.getAllProperties(0, 6).subscribe({
      next: (response) => {
        this.recentProperties = response.content;
      },
      error: (error) => {
        console.error('Error loading recent properties:', error);
      }
    });
  }

  onSearch(): void {
    this.router.navigate(['/search'], {
      queryParams: {
        propertyType: this.searchFilters.propertyType || undefined,
        listingType: this.searchFilters.listingType || undefined,
        city: this.searchFilters.city || undefined
      }
    });
  }
}
