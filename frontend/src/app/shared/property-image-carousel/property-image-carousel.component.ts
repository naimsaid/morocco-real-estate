import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-property-image-carousel',
  templateUrl: './property-image-carousel.component.html',
  styleUrls: ['./property-image-carousel.component.css']
})
export class PropertyImageCarouselComponent {
  @Input() alt = '';

  readonly placeholderImage = 'data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iNDAwIiBoZWlnaHQ9IjMwMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj48cmVjdCB3aWR0aD0iNDAwIiBoZWlnaHQ9IjMwMCIgZmlsbD0iI2NjYyIvPjx0ZXh0IHg9IjUwJSIgeT0iNTAlIiBkb21pbmFudC1iYXNlbGluZT0ibWlkZGxlIiB0ZXh0LWFuY2hvcj0ibWlkZGxlIiBmaWxsPSIjZmZmIiBmb250LXNpemU9IjIwIj5Qcm9wZXJ0eSBJbWFnZTwvdGV4dD48L3N2Zz4=';

  currentIndex = 0;
  private _images: string[] = [];

  @Input()
  set images(value: string[] | undefined | null) {
    this._images = value && value.length > 0 ? value : [];
    this.currentIndex = 0;
  }

  get displayImages(): string[] {
    return this._images.length > 0 ? this._images : [this.placeholderImage];
  }

  get currentImage(): string {
    return this.displayImages[this.currentIndex] || this.placeholderImage;
  }

  select(index: number, event?: Event): void {
    if (event) {
      event.stopPropagation();
      event.preventDefault();
    }
    const total = this.displayImages.length;
    if (total === 0) return;
    this.currentIndex = (index + total) % total;
  }

  next(event: Event): void {
    this.select(this.currentIndex + 1, event);
  }

  prev(event: Event): void {
    this.select(this.currentIndex - 1, event);
  }

  onImageError(event: any): void {
    event.target.src = this.placeholderImage;
  }
}
