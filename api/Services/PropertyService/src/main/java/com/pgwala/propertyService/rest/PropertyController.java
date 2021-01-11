package com.pgwala.propertyService.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgwala.propertyService.dto.ResponseDTO;
import com.pgwala.propertyService.entity.Property;
import com.pgwala.propertyService.service.PropertyService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

	@Autowired
	PropertyService propertyService;

	@GetMapping
	public ResponseDTO getAllProperties() {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<Property> properties = propertyService.getAllProperties();
			responseDTO.setData(properties);
			responseDTO.setMessage("operation successful.");
			responseDTO.setStatus(1);
			
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus(0);
		}
		return responseDTO;
	}

	@GetMapping("{id}")
	public ResponseDTO getPropertyById(@PathVariable("id") String id) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			Property property = propertyService.getPropertyById(id);
			responseDTO.setData(property);
			responseDTO.setMessage("operation successful.");
			responseDTO.setStatus(1);
			
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus(0);
		}
		return responseDTO;
	}

	@PostMapping("/{email}/addProperty")
	public ResponseDTO addProperty(@PathVariable("email") String email, @RequestBody Property property) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			Property propertyAdded = propertyService.addProperty(email, property);
			responseDTO.setData(propertyAdded);
			responseDTO.setMessage("operation successful.");
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus(0);
		}
		return responseDTO;
	}

	@PutMapping("/{email}/updateProperty/{id}")
	public ResponseDTO updateProperty(@PathVariable("email") String email, @PathVariable("id") String id,
			@RequestBody Property property) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			Property propertyUpdated = propertyService.updateProperty(email, id, property);
			responseDTO.setData(propertyUpdated);
			responseDTO.setMessage("operation successful.");
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus(0);
		}
		return responseDTO;
	}

	@DeleteMapping("/{email}/deleteProperty/{id}")
	public ResponseDTO deleteProperty(@PathVariable("email") String email, @PathVariable("id") String id) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			boolean deleteSuccess = propertyService.deleteProperty(email, id);
			responseDTO.setData(deleteSuccess);
			responseDTO.setMessage("operation successful.");
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus(0);
		}
		return responseDTO;
	}

}
