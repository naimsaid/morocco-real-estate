package com.realestate.morocco.dto;

import com.realestate.morocco.entity.PropertyType;
import com.realestate.morocco.entity.ListingType;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Objects;

@Data
public class SearchRequest {
    private PropertyType propertyType;
    private ListingType listingType;
    private String city;
    private String region;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Integer minBedrooms;
    private Integer minBathrooms;
    private Integer page = 0;
    private Integer size = 10;
    private String sortBy = "createdAt";
    private String sortDirection = "desc";
	public SearchRequest(PropertyType propertyType, ListingType listingType, String city, String region,
			BigDecimal minPrice, BigDecimal maxPrice, Integer minBedrooms, Integer minBathrooms, Integer page,
			Integer size, String sortBy, String sortDirection) {
		super();
		this.propertyType = propertyType;
		this.listingType = listingType;
		this.city = city;
		this.region = region;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.minBedrooms = minBedrooms;
		this.minBathrooms = minBathrooms;
		this.page = page;
		this.size = size;
		this.sortBy = sortBy;
		this.sortDirection = sortDirection;
	}
	public SearchRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(city, listingType, maxPrice, minBathrooms, minBedrooms, minPrice, page, propertyType,
				region, size, sortBy, sortDirection);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchRequest other = (SearchRequest) obj;
		return Objects.equals(city, other.city) && listingType == other.listingType
				&& Objects.equals(maxPrice, other.maxPrice) && Objects.equals(minBathrooms, other.minBathrooms)
				&& Objects.equals(minBedrooms, other.minBedrooms) && Objects.equals(minPrice, other.minPrice)
				&& Objects.equals(page, other.page) && propertyType == other.propertyType
				&& Objects.equals(region, other.region) && Objects.equals(size, other.size)
				&& Objects.equals(sortBy, other.sortBy) && Objects.equals(sortDirection, other.sortDirection);
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
	 * @return the minPrice
	 */
	public BigDecimal getMinPrice() {
		return minPrice;
	}
	/**
	 * @param minPrice the minPrice to set
	 */
	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}
	/**
	 * @return the maxPrice
	 */
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}
	/**
	 * @param maxPrice the maxPrice to set
	 */
	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
	/**
	 * @return the minBedrooms
	 */
	public Integer getMinBedrooms() {
		return minBedrooms;
	}
	/**
	 * @param minBedrooms the minBedrooms to set
	 */
	public void setMinBedrooms(Integer minBedrooms) {
		this.minBedrooms = minBedrooms;
	}
	/**
	 * @return the minBathrooms
	 */
	public Integer getMinBathrooms() {
		return minBathrooms;
	}
	/**
	 * @param minBathrooms the minBathrooms to set
	 */
	public void setMinBathrooms(Integer minBathrooms) {
		this.minBathrooms = minBathrooms;
	}
	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}
	/**
	 * @return the size
	 */
	public Integer getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(Integer size) {
		this.size = size;
	}
	/**
	 * @return the sortBy
	 */
	public String getSortBy() {
		return sortBy;
	}
	/**
	 * @param sortBy the sortBy to set
	 */
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	/**
	 * @return the sortDirection
	 */
	public String getSortDirection() {
		return sortDirection;
	}
	/**
	 * @param sortDirection the sortDirection to set
	 */
	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}
    
	
	
    
}
