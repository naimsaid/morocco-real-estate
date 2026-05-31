package com.realestate.morocco.repository;

import com.realestate.morocco.entity.PropertyImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyImageRepository extends JpaRepository<PropertyImage, Long> {
    
    List<PropertyImage> findByPropertyId(Long propertyId);
    List<PropertyImage> findByPropertyIdOrderByDisplayOrderAsc(Long propertyId);
}
