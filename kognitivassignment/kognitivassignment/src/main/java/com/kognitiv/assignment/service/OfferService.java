package com.kognitiv.assignment.service;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kognitiv.assignment.dao.IOfferDao;
import com.kognitiv.assignment.dto.ImageData;
import com.kognitiv.assignment.dto.Offer;
import com.kognitiv.assignment.dto.OffersDTO;
import com.kognitiv.assignment.exception.ApiRequestException;

@Service
public class OfferService implements IOfferService {
	@Autowired
	IOfferDao iOfferDao;
	
	@Value(value = "${image.base.uri}")
	private String BASE_URL;
	
	private List<String> imagelist=null;
	@Override
	public OffersDTO collectOffers(Integer id,Integer limit,Integer offset) throws ApiRequestException {
		OffersDTO offersDTO=new OffersDTO();
		List<Offer> offerList=new ArrayList<>();
		offersDTO.setData(offerList);
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(id>0) {
				Optional<com.kognitiv.assignment.entity.Offer> offerOp = iOfferDao.findById(id);
				if(offerOp.isPresent()) {
					com.kognitiv.assignment.entity.Offer offerval = offerOp.get();
					Offer dtoOffer=new Offer();
					dtoOffer.setOfferId(offerval.getId());
					dtoOffer.setOfferName(offerval.getName());
					dtoOffer.setOfferValidFrom(df.format(offerval.getValidFrom()));
					dtoOffer.setOfferValidTill(df.format(offerval.getValidTill()));
					dtoOffer.setOfferLocation(offerval.getLocation());
					dtoOffer.setImage(offerval.getImages());
					offerList.add(dtoOffer);
					offersDTO.setSuccess(Boolean.TRUE);
				}else
				{
					offersDTO.setSuccess(Boolean.FALSE);
				}
			}else {
				List<com.kognitiv.assignment.entity.Offer> offersListFromrDb=null;
				if(limit>0&&offset>1) {
					offersListFromrDb =iOfferDao.findAllPaged(limit, offset);
				}else {
					offersListFromrDb=iOfferDao.findAll();
				}
				offerList=offersListFromrDb.stream().map(a -> convertToDtoOffer(a, df)).collect(Collectors.toCollection(ArrayList::new));
				offersDTO.setData(offerList);
				offersDTO.setSuccess(Boolean.TRUE);
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new ApiRequestException("Error while fetching the offers : "+e.getMessage());
		}
		return offersDTO;
	}

	@Override
	public Boolean putOffers(Offer offer) throws ApiRequestException {
		if(offer.getImage()==null) {
			if(imagelist==null) {
				populateImageList();
			}
			Random random=new Random();
			int idx=Math.abs(random.nextInt())%imagelist.size();
			offer.setImage(imagelist.get(idx));
		}
		try {
			com.kognitiv.assignment.entity.Offer savedoffer = iOfferDao.saveAndFlush(convertToEntityOffer(offer));
			return savedoffer!=null;
		}catch(ParseException e) {
			e.printStackTrace();
			throw new ApiRequestException("Error while saving the offer : "+e.getMessage());
		}
	}
	private Offer convertToDtoOffer(com.kognitiv.assignment.entity.Offer offerval,DateFormat df) {
		Offer dto=new Offer();
		dto.setOfferId(offerval.getId());
		dto.setOfferName(offerval.getName());
		dto.setOfferValidFrom(df.format(offerval.getValidFrom()));
		dto.setOfferValidTill(df.format(offerval.getValidTill()));
		dto.setOfferLocation(offerval.getLocation());
		dto.setImage(offerval.getImages());
		return dto;
	}
	private com.kognitiv.assignment.entity.Offer convertToEntityOffer(Offer dto) throws ParseException{
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		com.kognitiv.assignment.entity.Offer offer=new com.kognitiv.assignment.entity.Offer();
		offer.setName(dto.getOfferName());
		offer.setValidFrom(new Timestamp(df.parse(dto.getOfferValidFrom()).getTime()));
		offer.setValidTill(new Timestamp(df.parse(dto.getOfferValidTill()).getTime()));
		offer.setLocation(dto.getOfferLocation());
		offer.setImages(dto.getImage());
		return offer;
	}
	private void populateImageList() {
		System.out.println(BASE_URL);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ImageData[]> responseEntity =
				   restTemplate.getForEntity(BASE_URL, ImageData[].class);
		ImageData[] imageDateArr = responseEntity.getBody();
		imagelist=Arrays.stream(imageDateArr).map(ImageData::getUrl).collect(Collectors.toList());
	}
}
