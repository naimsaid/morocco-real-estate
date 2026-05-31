package com.realestate.morocco.controller;

import com.realestate.morocco.entity.Favorite;
import com.realestate.morocco.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin(origins = "http://localhost:4200")
public class FavoriteController {
    
    private final FavoriteService favoriteService;
    
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }
    
    @PostMapping("/{propertyId}")
    public ResponseEntity<Favorite> addFavorite(@PathVariable Long propertyId,
                                               Authentication authentication) {
        return ResponseEntity.ok(favoriteService.addFavorite(propertyId, authentication));
    }
    
    @DeleteMapping("/{propertyId}")
    public ResponseEntity<Void> removeFavorite(@PathVariable Long propertyId,
                                             Authentication authentication) {
        favoriteService.removeFavorite(propertyId, authentication);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping
    public ResponseEntity<List<Favorite>> getUserFavorites(Authentication authentication) {
        return ResponseEntity.ok(favoriteService.getUserFavorites(authentication));
    }
    
    @GetMapping("/check/{propertyId}")
    public ResponseEntity<Boolean> isFavorite(@PathVariable Long propertyId,
                                              Authentication authentication) {
        return ResponseEntity.ok(favoriteService.isFavorite(propertyId, authentication));
    }
}
