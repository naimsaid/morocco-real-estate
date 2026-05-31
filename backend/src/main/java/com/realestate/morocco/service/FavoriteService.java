package com.realestate.morocco.service;

import com.realestate.morocco.entity.Favorite;
import com.realestate.morocco.entity.Property;
import com.realestate.morocco.entity.User;
import com.realestate.morocco.repository.FavoriteRepository;
import com.realestate.morocco.repository.PropertyRepository;
import com.realestate.morocco.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FavoriteService {
    
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    
    public FavoriteService(FavoriteRepository favoriteRepository, UserRepository userRepository,
                          PropertyRepository propertyRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
    }
    
    @Transactional
    public Favorite addFavorite(Long propertyId, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        
        if (favoriteRepository.existsByUserIdAndPropertyId(user.getId(), propertyId)) {
            throw new RuntimeException("Property already in favorites");
        }
        
        Favorite favorite = Favorite.builder()
                .user(user)
                .property(property)
                .build();
        
        return favoriteRepository.save(favorite);
    }
    
    @Transactional
    public void removeFavorite(Long propertyId, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (!favoriteRepository.existsByUserIdAndPropertyId(user.getId(), propertyId)) {
            throw new RuntimeException("Property not in favorites");
        }
        
        favoriteRepository.deleteByUserIdAndPropertyId(user.getId(), propertyId);
    }
    
    public List<Favorite> getUserFavorites(Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return favoriteRepository.findByUserId(user.getId());
    }
    
    public boolean isFavorite(Long propertyId, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return favoriteRepository.existsByUserIdAndPropertyId(user.getId(), propertyId);
    }
}
