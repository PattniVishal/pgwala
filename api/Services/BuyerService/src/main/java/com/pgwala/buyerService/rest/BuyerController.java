package com.pgwala.buyerService.rest;

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

import com.pgwala.buyerService.dto.BuyerDTO;
import com.pgwala.buyerService.dto.ResponseDTO;
import com.pgwala.buyerService.service.BuyerService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/buyers")
public class BuyerController {

	@Autowired
	BuyerService buyerService;

	@GetMapping
	public ResponseDTO getAllBuyers() {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<BuyerDTO> buyers = buyerService.getAllBuyers();
			responseDTO.setData(buyers);
			responseDTO.setMessage("operation successful.");
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus(0);
		}
		return responseDTO;
	}

	@GetMapping("{email}")
	public ResponseDTO getBuyer(@PathVariable("email") String email) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			BuyerDTO buyer = buyerService.getBuyer(email);
			responseDTO.setData(buyer);
			responseDTO.setMessage("operation successful.");
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus(0);
		}
		return responseDTO;
	}

	@PostMapping
	public ResponseDTO addBuyer(@RequestBody BuyerDTO buyerDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			BuyerDTO newBuyer = buyerService.addBuyer(buyerDTO);
			responseDTO.setData(newBuyer);
			responseDTO.setMessage("operation successful.");
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus(0);
		}
		return responseDTO;
	}

	@PutMapping("{email}")
	public ResponseDTO updateBuyer(@PathVariable("email") String email, @RequestBody BuyerDTO buyerDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			BuyerDTO updatedBuyer = buyerService.updateBuyer(email, buyerDTO);
			responseDTO.setData(updatedBuyer);
			responseDTO.setMessage("operation successful.");
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus(0);
		}
		return responseDTO;
	}

	@DeleteMapping("{email}")
	public ResponseDTO deleteBuyer(@PathVariable("email") String email) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			boolean deleteSuccess = buyerService.deleteBuyer(email);
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
