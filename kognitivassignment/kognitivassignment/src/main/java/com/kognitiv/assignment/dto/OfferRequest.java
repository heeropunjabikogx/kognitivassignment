package com.kognitiv.assignment.dto;

import io.swagger.annotations.ApiModelProperty;

public class OfferRequest extends Offer {
	@ApiModelProperty(hidden = true)
	@Override
	public String getImage() {
		return super.getImage();
	}
}
