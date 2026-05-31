package com.realestate.morocco.repository;

import com.realestate.morocco.entity.Property;
import com.realestate.morocco.entity.PropertyType;
import com.realestate.morocco.entity.ListingType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    
    Page<Property> findByFeaturedTrue(Pageable pageable);
    Page<Property> findByAvailableTrue(Pageable pageable);
    
    Page<Property> findByPropertyTypeAndListingTypeAndAvailableTrue(
        PropertyType propertyType, ListingType listingType, Pageable pageable
    );
    
    Page<Property> findByCityAndListingTypeAndAvailableTrue(
        String city, ListingType listingType, Pageable pageable
    );
    
    Page<Property> findByRegionAndListingTypeAndAvailableTrue(
        String region, ListingType listingType, Pageable pageable
    );
    
    @Query("SELECT p FROM Property p WHERE p.available = true AND " +
           "(:propertyType IS NULL OR p.propertyType = :propertyType) AND " +
           "(:listingType IS NULL OR p.listingType = :listingType) AND " +
           "(:city IS NULL OR :city = '' OR p.city = :city) AND " +
           "(:region IS NULL OR :region = '' OR p.region = :region) AND " +
           "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
           "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
           "(:minBedrooms IS NULL OR p.bedrooms >= :minBedrooms) AND " +
           "(:minBathrooms IS NULL OR p.bathrooms >= :minBathrooms)")
    Page<Property> searchProperties(
        @Param("propertyType") PropertyType propertyType,
        @Param("listingType") ListingType listingType,
        @Param("city") String city,
        @Param("region") String region,
        @Param("minPrice") BigDecimal minPrice,
        @Param("maxPrice") BigDecimal maxPrice,
        @Param("minBedrooms") Integer minBedrooms,
        @Param("minBathrooms") Integer minBathrooms,
        Pageable pageable
    );
    
    Page<Property> findByOwnerId(Long ownerId, Pageable pageable);
}
