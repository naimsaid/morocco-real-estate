package com.realestate.morocco.controller;

import com.realestate.morocco.dto.PropertyRequest;
import com.realestate.morocco.dto.PropertyResponse;
import com.realestate.morocco.dto.SearchRequest;
import com.realestate.morocco.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin(origins = "http://localhost:4200")
public class PropertyController {
    
    private final PropertyService propertyService;
    
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }
    
    @PostMapping
    public ResponseEntity<PropertyResponse> createProperty(@Valid @RequestBody PropertyRequest request,
                                                           Authentication authentication) {
        return ResponseEntity.ok(propertyService.createProperty(request, authentication));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponse> getPropertyById(@PathVariable Long id,
                                                           Authentication authentication) {
        return ResponseEntity.ok(propertyService.getPropertyById(id, authentication));
    }
    
    @GetMapping("/public/{id}")
    public ResponseEntity<PropertyResponse> getPropertyPublic(@PathVariable Long id) {
        return ResponseEntity.ok(propertyService.getPropertyById(id, null));
    }
    
    @GetMapping
    public ResponseEntity<Page<PropertyResponse>> getAllProperties(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication) {
        return ResponseEntity.ok(propertyService.getAllProperties(page, size, authentication));
    }
    
    @GetMapping("/public")
    public ResponseEntity<Page<PropertyResponse>> getPublicProperties(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(propertyService.getAllProperties(page, size, null));
    }
    
    @GetMapping("/featured")
    public ResponseEntity<Page<PropertyResponse>> getFeaturedProperties(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(propertyService.getFeaturedProperties(page, size));
    }
    
    @PostMapping("/search")
    public ResponseEntity<Page<PropertyResponse>> searchProperties(@RequestBody SearchRequest request,
                                                                   Authentication authentication) {
        return ResponseEntity.ok(propertyService.searchProperties(request, authentication));
    }

    @PostMapping("/public/search")
    public ResponseEntity<Page<PropertyResponse>> searchPropertiesPublic(@RequestBody SearchRequest request) {
        return ResponseEntity.ok(propertyService.searchProperties(request, null));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PropertyResponse> updateProperty(@PathVariable Long id,
                                                           @Valid @RequestBody PropertyRequest request,
                                                           Authentication authentication) {
        return ResponseEntity.ok(propertyService.updateProperty(id, request, authentication));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id,
                                              Authentication authentication) {
        propertyService.deleteProperty(id, authentication);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/my-properties")
    public ResponseEntity<Page<PropertyResponse>> getMyProperties(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication) {
        return ResponseEntity.ok(propertyService.getUserProperties(page, size, authentication));
    }
}
