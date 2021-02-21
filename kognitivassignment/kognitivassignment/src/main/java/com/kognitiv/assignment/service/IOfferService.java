package com.kognitiv.assignment.service;


import com.kognitiv.assignment.dto.Offer;
import com.kognitiv.assignment.dto.OffersDTO;
import com.kognitiv.assignment.exception.ApiRequestException;

public interface IOfferService {
	public OffersDTO collectOffers(Integer id,Integer limit,Integer offset) throws ApiRequestException;
	public Boolean putOffers(Offer offer) throws ApiRequestException;
}
