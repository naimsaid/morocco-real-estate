package com.realestate.morocco.service;

import com.realestate.morocco.dto.PropertyRequest;
import com.realestate.morocco.dto.PropertyResponse;
import com.realestate.morocco.dto.SearchRequest;
import com.realestate.morocco.entity.Favorite;
import com.realestate.morocco.entity.Property;
import com.realestate.morocco.entity.PropertyImage;
import com.realestate.morocco.entity.User;
import com.realestate.morocco.repository.FavoriteRepository;
import com.realestate.morocco.repository.PropertyImageRepository;
import com.realestate.morocco.repository.PropertyRepository;
import com.realestate.morocco.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {
    
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final PropertyImageRepository propertyImageRepository;
    private final FavoriteRepository favoriteRepository;
    
    public PropertyService(PropertyRepository propertyRepository, UserRepository userRepository,
                          PropertyImageRepository propertyImageRepository, FavoriteRepository favoriteRepository) {
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
        this.propertyImageRepository = propertyImageRepository;
        this.favoriteRepository = favoriteRepository;
    }
    
    @Transactional
    public PropertyResponse createProperty(PropertyRequest request, Authentication authentication) {
        User owner = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Property property = Property.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .propertyType(request.getPropertyType())
                .listingType(request.getListingType())
                .price(request.getPrice())
                .area(request.getArea())
                .bedrooms(request.getBedrooms())
                .bathrooms(request.getBathrooms())
                .floors(request.getFloors())
                .address(request.getAddress())
                .city(request.getCity())
                .region(request.getRegion())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .featured(request.getFeatured() != null ? request.getFeatured() : false)
                .available(true)
                .amenities(request.getAmenities())
                .owner(owner)
                .build();
        
        property = propertyRepository.save(property);
        saveImages(property, request.getImageUrls());
        return convertToResponse(property, null);
    }

    private void saveImages(Property property, List<String> imageUrls) {
        if (imageUrls == null || imageUrls.isEmpty()) {
            return;
        }
        List<PropertyImage> images = new ArrayList<>();
        int order = 1;
        for (String url : imageUrls) {
            if (url == null || url.trim().isEmpty()) {
                continue;
            }
            images.add(PropertyImage.builder()
                    .imageUrl(url)
                    .isPrimary(order == 1)
                    .displayOrder(order++)
                    .property(property)
                    .build());
        }
        if (!images.isEmpty()) {
            propertyImageRepository.saveAll(images);
        }
    }
    
    public PropertyResponse getPropertyById(Long id, Authentication authentication) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        
        Long userId = null;
        if (authentication != null && authentication.isAuthenticated()) {
            User user = userRepository.findByEmail(authentication.getName()).orElse(null);
            if (user != null) {
                userId = user.getId();
            }
        }
        
        return convertToResponse(property, userId);
    }
    
    public Page<PropertyResponse> getAllProperties(int page, int size, Authentication authentication) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Property> properties = propertyRepository.findByAvailableTrue(pageable);
        
        final Long userId;
        if (authentication != null && authentication.isAuthenticated()) {
            User user = userRepository.findByEmail(authentication.getName()).orElse(null);
            userId = user != null ? user.getId() : null;
        } else {
            userId = null;
        }
        
        return properties.map(property -> convertToResponse(property, userId));
    }
    
    public Page<PropertyResponse> getFeaturedProperties(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Property> properties = propertyRepository.findByFeaturedTrue(pageable);
        return properties.map(property -> convertToResponse(property, null));
    }
    
    public Page<PropertyResponse> searchProperties(SearchRequest request, Authentication authentication) {
        Sort sort = Sort.by(
                request.getSortDirection().equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                request.getSortBy()
        );
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        
        Page<Property> properties = propertyRepository.searchProperties(
                request.getPropertyType(),
                request.getListingType(),
                request.getCity(),
                request.getRegion(),
                request.getMinPrice(),
                request.getMaxPrice(),
                request.getMinBedrooms(),
                request.getMinBathrooms(),
                pageable
        );
        
        final Long userId;
        if (authentication != null && authentication.isAuthenticated()) {
            User user = userRepository.findByEmail(authentication.getName()).orElse(null);
            userId = user != null ? user.getId() : null;
        } else {
            userId = null;
        }
        
        return properties.map(property -> convertToResponse(property, userId));
    }
    
    @Transactional
    public PropertyResponse updateProperty(Long id, PropertyRequest request, Authentication authentication) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        
        User currentUser = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (!property.getOwner().getId().equals(currentUser.getId()) && 
            !currentUser.getRole().name().equals("ADMIN")) {
            throw new RuntimeException("Not authorized to update this property");
        }
        
        property.setTitle(request.getTitle());
        property.setDescription(request.getDescription());
        property.setPropertyType(request.getPropertyType());
        property.setListingType(request.getListingType());
        property.setPrice(request.getPrice());
        property.setArea(request.getArea());
        property.setBedrooms(request.getBedrooms());
        property.setBathrooms(request.getBathrooms());
        property.setFloors(request.getFloors());
        property.setAddress(request.getAddress());
        property.setCity(request.getCity());
        property.setRegion(request.getRegion());
        property.setLatitude(request.getLatitude());
        property.setLongitude(request.getLongitude());
        property.setFeatured(request.getFeatured());
        property.setAmenities(request.getAmenities());
        
        property = propertyRepository.save(property);

        if (request.getImageUrls() != null) {
            propertyImageRepository.deleteAll(propertyImageRepository.findByPropertyId(property.getId()));
            saveImages(property, request.getImageUrls());
        }

        return convertToResponse(property, currentUser.getId());
    }
    
    @Transactional
    public void deleteProperty(Long id, Authentication authentication) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        
        User currentUser = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (!property.getOwner().getId().equals(currentUser.getId()) && 
            !currentUser.getRole().name().equals("ADMIN")) {
            throw new RuntimeException("Not authorized to delete this property");
        }
        
        propertyRepository.delete(property);
    }
    
    public Page<PropertyResponse> getUserProperties(int page, int size, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Property> properties = propertyRepository.findByOwnerId(user.getId(), pageable);
        
        return properties.map(property -> convertToResponse(property, user.getId()));
    }
    
    private PropertyResponse convertToResponse(Property property, Long userId) {
        var imageUrls = propertyImageRepository.findByPropertyIdOrderByDisplayOrderAsc(property.getId())
                .stream()
                .map(PropertyImage::getImageUrl)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        
        String primaryImage = imageUrls.stream().findFirst().orElse(null);
        
        Boolean isFavorite = false;
        if (userId != null) {
            isFavorite = favoriteRepository.existsByUserIdAndPropertyId(userId, property.getId());
        }
        
        return new PropertyResponse(
                property.getId(),
                property.getTitle(),
                property.getDescription(),
                property.getPropertyType(),
                property.getListingType(),
                property.getPrice(),
                property.getArea(),
                property.getBedrooms(),
                property.getBathrooms(),
                property.getFloors(),
                property.getAddress(),
                property.getCity(),
                property.getRegion(),
                property.getLatitude(),
                property.getLongitude(),
                property.getFeatured(),
                property.getAvailable(),
                property.getCreatedAt(),
                property.getUpdatedAt(),
                property.getOwner().getId(),
                property.getOwner().getFirstName() + " " + property.getOwner().getLastName(),
                property.getOwner().getEmail(),
                property.getOwner().getPhone(),
                imageUrls,
                primaryImage,
                property.getAmenities(),
                isFavorite
        );
    }
}
