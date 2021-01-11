package com.pgwala.propertyService.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgwala.propertyService.dao.PropertyDAO;
import com.pgwala.propertyService.dao.SellerDAO;
import com.pgwala.propertyService.entity.Property;
import com.pgwala.propertyService.entity.SellerEntity;
import com.pgwala.propertyService.exception.PropertyNotFoundException;
import com.pgwala.propertyService.exception.UserNotFoundException;
import com.pgwala.propertyService.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	PropertyDAO propertyDAO;

	@Autowired
	SellerDAO sellerDAO;

	@Override
	public List<Property> getAllProperties() {
		return propertyDAO.findAll();
	}

	@Override
	public Property getPropertyById(String id) throws PropertyNotFoundException {
		Optional<Property> newProperty = propertyDAO.findById(id);
		if (newProperty.isPresent()) {
			return newProperty.get();
		} else {
			throw new PropertyNotFoundException("property with propertyId " + id + " not found.");
		}
	}

	@Override
	public Property addProperty(String email, Property property) throws UserNotFoundException {
		SellerEntity sellerEntity = sellerDAO.findByEmail(email);
		if (sellerEntity != null) {
			List<Property> listOfProperty = new ArrayList<Property>();
			if (!sellerEntity.getProperties().isEmpty()) {
				listOfProperty = sellerEntity.getProperties();
			}
			property.setSellerName(sellerEntity.getName());
			property.setSellerEmail(email);
			Property newProperty = propertyDAO.save(property);
			listOfProperty.add(newProperty);
			sellerEntity.setProperties(listOfProperty);
			sellerDAO.save(sellerEntity);
			return newProperty;
		} else {
			throw new UserNotFoundException("user with username " + email + " not found.");
		}
	}

	@Override
	public Property updateProperty(String email, String id, Property property)
			throws UserNotFoundException, PropertyNotFoundException {
		if (sellerDAO.existsByEmail(email)) {
			if (propertyDAO.existsById(id)) {
				Property updatedProperty = propertyDAO.save(property);

				SellerEntity sellerEntity = sellerDAO.findByEmail(email);
				List<Property> listOfProperty = new ArrayList<Property>();
				if (!sellerEntity.getProperties().isEmpty()) {
					listOfProperty = sellerEntity.getProperties().stream().map(propertyItem -> {
						if (propertyItem.getId().equals(updatedProperty.getId())) {
							return updatedProperty;
						} else {
							return propertyItem;
						}
					}).collect(Collectors.toList());
				}
				sellerEntity.setProperties(listOfProperty);
				sellerDAO.save(sellerEntity);
				return updatedProperty;
			} else {
				throw new PropertyNotFoundException("property with propertyId " + id + " not found.");
			}
		} else {
			throw new UserNotFoundException("user with username " + email + " not found.");
		}
	}

	@Override
	public boolean deleteProperty(String email, String id) throws UserNotFoundException, PropertyNotFoundException {
		Optional<Property> propertyToDelete = propertyDAO.findById(id);
		if (sellerDAO.existsByEmail(email)) {

			if (propertyDAO.existsById(id)) {
				SellerEntity sellerEntity = sellerDAO.findByEmail(email);
				List<Property> listOfProperty = new ArrayList<Property>();
				if (!sellerEntity.getProperties().isEmpty()) {
					listOfProperty = sellerEntity.getProperties().stream()
							.filter(propertyItem -> !(propertyItem.getId().equals(propertyToDelete.get().getId())))
							.collect(Collectors.toList());
				}
				sellerEntity.setProperties(listOfProperty);
				sellerDAO.save(sellerEntity);

				propertyDAO.delete(propertyToDelete.get());
				return true;
			} else {
				throw new PropertyNotFoundException("property with propertyId " + id + " not found.");
			}
		} else {
			throw new UserNotFoundException("user with username " + email + " not found.");
		}
	}

}
