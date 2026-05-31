import { Component, OnInit } from '@angular/core';
import { FavoriteService } from '../services/favorite.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.css']
})
export class FavoritesComponent implements OnInit {
  favorites: any[] = [];
  loading = true;

  constructor(private favoriteService: FavoriteService, private router: Router) {}

  ngOnInit(): void {
    this.loadFavorites();
  }

  loadFavorites(): void {
    this.favoriteService.getUserFavorites().subscribe({
      next: (favorites) => {
        this.favorites = favorites;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading favorites:', error);
        this.loading = false;
      }
    });
  }

  removeFavorite(propertyId: number): void {
    this.favoriteService.removeFavorite(propertyId).subscribe({
      next: () => {
        this.favorites = this.favorites.filter(f => f.property.id !== propertyId);
      },
      error: (error) => {
        console.error('Error removing favorite:', error);
      }
    });
  }

  viewProperty(propertyId: number): void {
    this.router.navigate(['/properties', propertyId]);
  }
}
