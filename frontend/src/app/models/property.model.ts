export interface Property {
  id: number;
  title: string;
  description: string;
  propertyType: PropertyType;
  listingType: ListingType;
  price: number;
  area: number;
  bedrooms?: number;
  bathrooms?: number;
  floors?: number;
  address: string;
  city: string;
  region: string;
  latitude?: number;
  longitude?: number;
  featured: boolean;
  available: boolean;
  createdAt: string;
  updatedAt: string;
  ownerId: number;
  ownerName: string;
  ownerEmail: string;
  ownerPhone: string;
  imageUrls: string[];
  primaryImage?: string;
  amenities: string[];
  isFavorite?: boolean;
}

export enum PropertyType {
  APARTMENT = 'APARTMENT',
  VILLA = 'VILLA',
  HOUSE = 'HOUSE',
  LAND = 'LAND',
  COMMERCIAL = 'COMMERCIAL',
  OFFICE = 'OFFICE',
  STUDIO = 'STUDIO',
  PENTHOUSE = 'PENTHOUSE',
  RIAD = 'RIAD',
  FARM = 'FARM'
}

export enum ListingType {
  SALE = 'SALE',
  RENT = 'RENT'
}

export interface PropertyRequest {
  title: string;
  description: string;
  propertyType: PropertyType;
  listingType: ListingType;
  price: number;
  area: number;
  bedrooms?: number;
  bathrooms?: number;
  floors?: number;
  address: string;
  city: string;
  region: string;
  latitude?: number;
  longitude?: number;
  featured?: boolean;
  amenities?: string[];
  imageUrls?: string[];
}

export interface SearchRequest {
  propertyType?: PropertyType;
  listingType?: ListingType;
  city?: string;
  region?: string;
  minPrice?: number;
  maxPrice?: number;
  minBedrooms?: number;
  minBathrooms?: number;
  page?: number;
  size?: number;
  sortBy?: string;
  sortDirection?: string;
}
