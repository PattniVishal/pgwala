package com.pgwala.sellerService.rest;

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

import com.pgwala.sellerService.dto.ResponseDTO;
import com.pgwala.sellerService.dto.SellerDTO;
import com.pgwala.sellerService.service.SellerService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/sellers")
public class SellerController {

	@Autowired
	SellerService sellerService;

	@GetMapping
	public ResponseDTO getAllSellers() {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<SellerDTO> sellers = sellerService.getAllSellers();
			responseDTO.setData(sellers);
			responseDTO.setMessage("operation successful.");
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus(0);
		}
		return responseDTO;
	}

	@GetMapping("{email}")
	public ResponseDTO getSeller(@PathVariable("email") String email) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			SellerDTO seller = sellerService.getSeller(email);
			responseDTO.setData(seller);
			responseDTO.setMessage("operation successful.");
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus(0);
		}
		return responseDTO;
	}

	@PostMapping
	public ResponseDTO addSeller(@RequestBody SellerDTO sellerDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			SellerDTO newSeller = sellerService.addSeller(sellerDTO);
			responseDTO.setData(newSeller);
			responseDTO.setMessage("operation successful.");
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus(0);
		}
		return responseDTO;
	}

	@PutMapping("{email}")
	public ResponseDTO updateSeller(@PathVariable("email") String email, @RequestBody SellerDTO sellerDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			SellerDTO updatedSeller = sellerService.updateSeller(email, sellerDTO);
			responseDTO.setData(updatedSeller);
			responseDTO.setMessage("operation successful.");
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			responseDTO.setStatus(0);
		}
		return responseDTO;
	}

	@DeleteMapping("{email}")
	public ResponseDTO deleteSeller(@PathVariable("email") String email) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			boolean deleteSuccess = sellerService.deleteSeller(email);
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
