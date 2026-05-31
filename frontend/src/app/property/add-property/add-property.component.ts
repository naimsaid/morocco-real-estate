import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PropertyService } from '../../services/property.service';
import { ToastrService } from 'ngx-toastr';
import { PropertyType, ListingType } from '../../models/property.model';

@Component({
  selector: 'app-add-property',
  templateUrl: './add-property.component.html',
  styleUrls: ['./add-property.component.css']
})
export class AddPropertyComponent {
  propertyForm: FormGroup;
  loading = false;
  amenities: string[] = [];
  imagePreviews: string[] = [];
  readonly maxImages = 10;
  readonly maxFileSizeMb = 5;

  propertyTypes = Object.values(PropertyType);
  listingTypes = Object.values(ListingType);
  cities = ['Casablanca', 'Rabat', 'Marrakech', 'Fès', 'Tanger', 'Agadir', 'Oujda', 'Meknès'];
  regions = ['Casablanca-Settat', 'Rabat-Salé-Kénitra', 'Marrakech-Safi', 'Fès-Meknès', 'Tanger-Tétouan-Al Hoceïma', 'Souss-Massa', 'L\'Oriental'];

  constructor(
    private fb: FormBuilder,
    private propertyService: PropertyService,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.propertyForm = this.fb.group({
      title: ['', [Validators.required, Validators.minLength(10)]],
      description: ['', [Validators.required, Validators.minLength(50)]],
      propertyType: [PropertyType.APARTMENT, Validators.required],
      listingType: [ListingType.SALE, Validators.required],
      price: ['', [Validators.required, Validators.min(0)]],
      area: ['', [Validators.required, Validators.min(1)]],
      bedrooms: ['', [Validators.min(1)]],
      bathrooms: ['', [Validators.min(1)]],
      floors: ['', [Validators.min(1)]],
      address: ['', Validators.required],
      city: ['', Validators.required],
      region: ['', Validators.required],
      latitude: [''],
      longitude: [''],
      featured: [false],
      amenityInput: ['']
    });
  }

  addAmenity(): void {
    const amenity = this.propertyForm.get('amenityInput')?.value;
    if (amenity && amenity.trim() && !this.amenities.includes(amenity.trim())) {
      this.amenities.push(amenity.trim());
      this.propertyForm.get('amenityInput')?.setValue('');
    }
  }

  removeAmenity(index: number): void {
    this.amenities.splice(index, 1);
  }

  onFilesSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (!input.files || input.files.length === 0) {
      return;
    }

    const files = Array.from(input.files);
    for (const file of files) {
      if (this.imagePreviews.length >= this.maxImages) {
        this.toastr.warning(`Vous pouvez ajouter au maximum ${this.maxImages} images`);
        break;
      }
      if (!file.type.startsWith('image/')) {
        this.toastr.error(`${file.name} n'est pas une image valide`);
        continue;
      }
      if (file.size > this.maxFileSizeMb * 1024 * 1024) {
        this.toastr.error(`${file.name} dépasse ${this.maxFileSizeMb} Mo`);
        continue;
      }

      const reader = new FileReader();
      reader.onload = () => {
        this.imagePreviews.push(reader.result as string);
      };
      reader.readAsDataURL(file);
    }

    input.value = '';
  }

  removeImage(index: number): void {
    this.imagePreviews.splice(index, 1);
  }

  moveImage(index: number, direction: number): void {
    const target = index + direction;
    if (target < 0 || target >= this.imagePreviews.length) {
      return;
    }
    const [moved] = this.imagePreviews.splice(index, 1);
    this.imagePreviews.splice(target, 0, moved);
  }

  onSubmit(): void {
    if (this.propertyForm.invalid) {
      this.toastr.error('Veuillez remplir tous les champs requis');
      return;
    }

    this.loading = true;
    const propertyRequest = {
      title: this.propertyForm.value.title,
      description: this.propertyForm.value.description,
      propertyType: this.propertyForm.value.propertyType,
      listingType: this.propertyForm.value.listingType,
      price: this.propertyForm.value.price,
      area: this.propertyForm.value.area,
      bedrooms: this.propertyForm.value.bedrooms || null,
      bathrooms: this.propertyForm.value.bathrooms || null,
      floors: this.propertyForm.value.floors || null,
      address: this.propertyForm.value.address,
      city: this.propertyForm.value.city,
      region: this.propertyForm.value.region,
      latitude: this.propertyForm.value.latitude || null,
      longitude: this.propertyForm.value.longitude || null,
      featured: this.propertyForm.value.featured,
      amenities: this.amenities,
      imageUrls: this.imagePreviews
    };

    this.propertyService.createProperty(propertyRequest).subscribe({
      next: () => {
        this.toastr.success('Propriété ajoutée avec succès!');
        this.router.navigate(['/dashboard']);
        this.loading = false;
      },
      error: (error) => {
        this.toastr.error('Erreur lors de l\'ajout de la propriété');
        this.loading = false;
      }
    });
  }
}
