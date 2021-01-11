package com.pgwala.propertyService.service;

import java.util.List;

import com.pgwala.propertyService.entity.Property;
import com.pgwala.propertyService.exception.PropertyNotFoundException;
import com.pgwala.propertyService.exception.UserNotFoundException;

public interface PropertyService {

	public List<Property> getAllProperties();
	
	public Property getPropertyById(String id) throws PropertyNotFoundException;
	
	public Property addProperty(String email, Property property) throws UserNotFoundException;
	
	public Property updateProperty(String email, String id, Property property) throws UserNotFoundException, PropertyNotFoundException;
	
	public boolean deleteProperty(String email, String id) throws UserNotFoundException, PropertyNotFoundException;
	
}
