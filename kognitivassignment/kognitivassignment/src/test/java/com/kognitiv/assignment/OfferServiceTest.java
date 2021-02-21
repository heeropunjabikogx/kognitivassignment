package com.kognitiv.assignment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.FormLoginRequestBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.kognitiv.assignment.dto.Offer;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.Principal;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class OfferServiceTest {
	
	@Autowired
    private MockMvc mvc;

	
	@Test
	@DisplayName("adding a new offer to the database")
	@WithMockUser(username = "user", password = "pass", roles = "USER")
	void additionOfNewOffers() throws Exception {
		String offer="{"
				+ "      \"offerId\": \"1\","
				+ "      \"offerName\": \"NEWYEAR2021\","
				+ "      \"offerValidFrom\": \"2020-10-10\","
				+ "      \"offerValidTill\": \"2021-10-10\","
				+ "      \"offerLocation\": \"Bombay\""
				+ "    }";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/collect/offer")
				.accept(MediaType.APPLICATION_JSON)
				.content(offer)
				.contentType(MediaType.APPLICATION_JSON);


		mvc.perform(requestBuilder).andExpect(status().isOk())
		.andExpect(content().string(containsString("{\"success\":\"true\"}")));
		;
	}
	@Test
	@DisplayName("fetching all offers from the database")
	@WithMockUser(username = "user", password = "pass", roles = "USER")
	void fetchOffers() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/collect/offer")
				.accept(MediaType.APPLICATION_JSON);


		mvc.perform(requestBuilder).andExpect(status().isOk());
		;
	}

}
