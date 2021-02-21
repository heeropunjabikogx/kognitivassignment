package com.kognitiv.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kognitiv.assignment.dto.Offer;
import com.kognitiv.assignment.dto.OfferRequest;
import com.kognitiv.assignment.dto.OffersDTO;
import com.kognitiv.assignment.dto.StatusDTO;
import com.kognitiv.assignment.service.IOfferService;
@CrossOrigin
@RestController
@RequestMapping("/collect")
public class OfferController {
	@Autowired
	IOfferService iofferService;
	
	@GetMapping({"/offer","/offer/{id}"})
	public ResponseEntity<OffersDTO> getoffer(@PathVariable(name ="id",required = false) Integer id,
			@RequestParam(value = "limit",defaultValue = "-1") Integer limit,
			@RequestParam(value = "offset",defaultValue = "-1") Integer offset){
		if(id==null)
			id=-1;
		OffersDTO offer=iofferService.collectOffers( id, limit, offset);
		return new ResponseEntity<>(offer,HttpStatus.OK);
	}
	@PostMapping("/offer")
	public ResponseEntity<StatusDTO> putOffer(
			@RequestBody OfferRequest offer){
		StatusDTO statusDTO=new StatusDTO();
		Boolean status=iofferService.putOffers(offer);
		statusDTO.setSuccess(String.valueOf(status));
		return  new ResponseEntity<>(statusDTO,HttpStatus.OK);
		
	}
}
