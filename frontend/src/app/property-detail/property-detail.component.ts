import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PropertyService } from '../services/property.service';
import { FavoriteService } from '../services/favorite.service';
import { AuthService } from '../services/auth.service';
import { Property } from '../models/property.model';

@Component({
  selector: 'app-property-detail',
  templateUrl: './property-detail.component.html',
  styleUrls: ['./property-detail.component.css']
})
export class PropertyDetailComponent implements OnInit {
  property: Property | null = null;
  loading = true;
  isFavorite = false;
  currentImageIndex = 0;

  readonly placeholderImage = 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iODAwIiBoZWlnaHQ9IjYwMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj48cmVjdCB3aWR0aD0iODAwIiBoZWlnaHQ9IjYwMCIgZmlsbD0iI2NjYyIvPjx0ZXh0IHg9IjUwJSIgeT0iNTAlIiBkb21pbmFudC1iYXNlbGluZT0ibWlkZGxlIiB0ZXh0LWFuY2hvcj0ibWlkZGxlIiBmaWxsPSIjZmZmIiBmb250LXNpemU9IjMwIj5Qcm9wZXJ0eSBJbWFnZTwvdGV4dD48L3N2Zz4=';

  constructor(
    private route: ActivatedRoute,
    private propertyService: PropertyService,
    private favoriteService: FavoriteService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.loadProperty(+id);
    }
  }

  loadProperty(id: number): void {
    this.propertyService.getPropertyById(id).subscribe({
      next: (property) => {
        this.property = property;
        this.isFavorite = property.isFavorite || false;
        this.currentImageIndex = 0;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading property:', error);
        this.loading = false;
      }
    });
  }

  toggleFavorite(): void {
    if (!this.authService.isLoggedIn()) {
      this.router.navigate(['/login']);
      return;
    }

    if (!this.property) return;

    if (this.isFavorite) {
      this.favoriteService.removeFavorite(this.property.id).subscribe({
        next: () => {
          this.isFavorite = false;
        },
        error: (error) => console.error('Error removing favorite:', error)
      });
    } else {
      this.favoriteService.addFavorite(this.property.id).subscribe({
        next: () => {
          this.isFavorite = true;
        },
        error: (error) => console.error('Error adding favorite:', error)
      });
    }
  }

  contactAgent(): void {
    if (!this.authService.isLoggedIn()) {
      this.router.navigate(['/login']);
      return;
    }
    // Implement contact functionality
    alert('Fonctionnalité de contact bientôt disponible!');
  }

  get galleryImages(): string[] {
    const images = this.property?.imageUrls && this.property.imageUrls.length > 0
      ? this.property.imageUrls
      : (this.property?.primaryImage ? [this.property.primaryImage] : []);
    return images.length > 0 ? images : [this.placeholderImage];
  }

  get currentImage(): string {
    return this.galleryImages[this.currentImageIndex] || this.placeholderImage;
  }

  selectImage(index: number): void {
    const total = this.galleryImages.length;
    if (total === 0) return;
    this.currentImageIndex = (index + total) % total;
  }

  nextImage(): void {
    this.selectImage(this.currentImageIndex + 1);
  }

  prevImage(): void {
    this.selectImage(this.currentImageIndex - 1);
  }

  onImageError(event: any): void {
    event.target.src = this.placeholderImage;
  }
}
