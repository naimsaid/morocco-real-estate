package com.realestate.morocco.dto;

import com.realestate.morocco.entity.PropertyType;
import com.realestate.morocco.entity.ListingType;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
public class PropertyRequest {
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
    private Set<String> amenities;
    private List<String> imageUrls;

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the propertyType
	 */
	public PropertyType getPropertyType() {
		return propertyType;
	}
	/**
	 * @param propertyType the propertyType to set
	 */
	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}
	/**
	 * @return the listingType
	 */
	public ListingType getListingType() {
		return listingType;
	}
	/**
	 * @param listingType the listingType to set
	 */
	public void setListingType(ListingType listingType) {
		this.listingType = listingType;
	}
	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * @return the area
	 */
	public Integer getArea() {
		return area;
	}
	/**
	 * @param area the area to set
	 */
	public void setArea(Integer area) {
		this.area = area;
	}
	/**
	 * @return the bedrooms
	 */
	public Integer getBedrooms() {
		return bedrooms;
	}
	/**
	 * @param bedrooms the bedrooms to set
	 */
	public void setBedrooms(Integer bedrooms) {
		this.bedrooms = bedrooms;
	}
	/**
	 * @return the bathrooms
	 */
	public Integer getBathrooms() {
		return bathrooms;
	}
	/**
	 * @param bathrooms the bathrooms to set
	 */
	public void setBathrooms(Integer bathrooms) {
		this.bathrooms = bathrooms;
	}
	/**
	 * @return the floors
	 */
	public Integer getFloors() {
		return floors;
	}
	/**
	 * @param floors the floors to set
	 */
	public void setFloors(Integer floors) {
		this.floors = floors;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the featured
	 */
	public Boolean getFeatured() {
		return featured;
	}
	/**
	 * @param featured the featured to set
	 */
	public void setFeatured(Boolean featured) {
		this.featured = featured;
	}
	/**
	 * @return the amenities
	 */
	public Set<String> getAmenities() {
		return amenities;
	}
	/**
	 * @param amenities the amenities to set
	 */
	public void setAmenities(Set<String> amenities) {
		this.amenities = amenities;
	}
	public PropertyRequest(String title, String description, PropertyType propertyType, ListingType listingType,
			BigDecimal price, Integer area, Integer bedrooms, Integer bathrooms, Integer floors, String address,
			String city, String region, Double latitude, Double longitude, Boolean featured, Set<String> amenities) {
		super();
		this.title = title;
		this.description = description;
		this.propertyType = propertyType;
		this.listingType = listingType;
		this.price = price;
		this.area = area;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.floors = floors;
		this.address = address;
		this.city = city;
		this.region = region;
		this.latitude = latitude;
		this.longitude = longitude;
		this.featured = featured;
		this.amenities = amenities;
	}
	public PropertyRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, amenities, area, bathrooms, bedrooms, city, description, featured, floors,
				latitude, listingType, longitude, price, propertyType, region, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PropertyRequest other = (PropertyRequest) obj;
		return Objects.equals(address, other.address) && Objects.equals(amenities, other.amenities)
				&& Objects.equals(area, other.area) && Objects.equals(bathrooms, other.bathrooms)
				&& Objects.equals(bedrooms, other.bedrooms) && Objects.equals(city, other.city)
				&& Objects.equals(description, other.description) && Objects.equals(featured, other.featured)
				&& Objects.equals(floors, other.floors) && Objects.equals(latitude, other.latitude)
				&& listingType == other.listingType && Objects.equals(longitude, other.longitude)
				&& Objects.equals(price, other.price) && propertyType == other.propertyType
				&& Objects.equals(region, other.region) && Objects.equals(title, other.title);
	}
    
    
}
