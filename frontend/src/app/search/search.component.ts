import { Component, OnInit } from '@angular/core';
import { PropertyService } from '../services/property.service';
import { Property, SearchRequest, PropertyType, ListingType } from '../models/property.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  properties: Property[] = [];
  loading = false;
  currentPage = 0;
  pageSize = 12;
  totalPages = 0;

  searchRequest: SearchRequest = {
    propertyType: undefined,
    listingType: undefined,
    city: '',
    region: '',
    minPrice: undefined,
    maxPrice: undefined,
    minBedrooms: undefined,
    minBathrooms: undefined,
    page: 0,
    size: 12,
    sortBy: 'createdAt',
    sortDirection: 'desc'
  };

  propertyTypes = Object.values(PropertyType);
  listingTypes = Object.values(ListingType);
  cities = ['Casablanca', 'Rabat', 'Marrakech', 'Fès', 'Tanger', 'Agadir', 'Oujda', 'Meknès'];
  regions = ['Casablanca-Settat', 'Rabat-Salé-Kénitra', 'Marrakech-Safi', 'Fès-Meknès', 'Tanger-Tétouan-Al Hoceïma', 'Souss-Massa', 'L\'Oriental'];

  constructor(private propertyService: PropertyService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      if (params['propertyType']) {
        this.searchRequest.propertyType = params['propertyType'];
      }
      if (params['listingType']) {
        this.searchRequest.listingType = params['listingType'];
      }
      if (params['city']) {
        this.searchRequest.city = params['city'];
      }
      this.search();
    });
  }

  search(): void {
    this.loading = true;
    this.searchRequest.page = this.currentPage;
    this.searchRequest.size = this.pageSize;

    const payload: SearchRequest = {
      propertyType: this.searchRequest.propertyType || undefined,
      listingType: this.searchRequest.listingType || undefined,
      city: this.searchRequest.city || undefined,
      region: this.searchRequest.region || undefined,
      minPrice: this.searchRequest.minPrice ?? undefined,
      maxPrice: this.searchRequest.maxPrice ?? undefined,
      minBedrooms: this.searchRequest.minBedrooms ?? undefined,
      minBathrooms: this.searchRequest.minBathrooms ?? undefined,
      page: this.currentPage,
      size: this.pageSize,
      sortBy: this.searchRequest.sortBy,
      sortDirection: this.searchRequest.sortDirection
    };

    this.propertyService.searchProperties(payload).subscribe({
      next: (response) => {
        this.properties = response.content;
        this.totalPages = response.totalPages;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error searching properties:', error);
        this.loading = false;
      }
    });
  }

  onSearch(): void {
    this.currentPage = 0;
    this.search();
  }

  onReset(): void {
    this.searchRequest = {
      propertyType: undefined,
      listingType: undefined,
      city: '',
      region: '',
      minPrice: undefined,
      maxPrice: undefined,
      minBedrooms: undefined,
      minBathrooms: undefined,
      page: 0,
      size: 12,
      sortBy: 'createdAt',
      sortDirection: 'desc'
    };
    this.currentPage = 0;
    this.search();
  }

  onPageChange(page: number): void {
    this.currentPage = page;
    this.search();
  }
}
