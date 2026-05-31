package com.realestate.morocco.dto;

import com.realestate.morocco.entity.PropertyType;
import com.realestate.morocco.entity.ListingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyResponse {
    private Long id;
    private String title;
    private String description;
    private PropertyType propertyType;
    private ListingType listingType;
    private BigDecimal price;
    private Integer area;
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer floors;
    private String address;
    private String city;
    private String region;
    private Double latitude;
    private Double longitude;
    private Boolean featured;
    private Boolean available;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long ownerId;
    private String ownerName;
    private String ownerEmail;
    private String ownerPhone;
    private Set<String> imageUrls;
    private String primaryImage;
    private Set<String> amenities;
    private Boolean isFavorite;
}
