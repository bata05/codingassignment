package com.devstaff.codingassignment;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.devstaff.codingassignment.controller.FarmController;
import com.devstaff.codingassignment.entity.Farm;
import com.devstaff.codingassignment.service.FarmService;

@WebMvcTest(FarmController.class)
@ActiveProfiles("test")
class CodingassignmentApplicationTests {
	@MockBean
	private FarmService farmServiceMock;
	
	@Autowired
	private MockMvc mockMvc;

	
	@Test
	public void testPlanted() throws Exception {
		
		Farm farmData = new Farm();
		farmData.setAcres("12");
		farmData.setCropType("potato");
		farmData.setSeason("January");
		farmData.setPlantedCount(200);
		
		
		
		// Mocking out the vehicle service
		Mockito.when(farmServiceMock.findFarmByAcresCropTypeSeason(any(Farm.class))).thenReturn(farmData);
//		Mockito.when(transactionLedgerService.fetchTransactionLedgerByConstraints(
//								anyString(), any(Date.class), any(Date.class))).thenReturn(listOfTransactionLedgers);
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/v1/planted")
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content("{\"acres\": \"12\", \"cropType\": \"potato\", \"season\": \"February\", \"plantedCount\": \"200\"}");

		mockMvc.perform(builder).andExpect(status().isOk());
	}
	
	@Test
	public void testHarvested() throws Exception {
		
		Farm farmData = new Farm();
		farmData.setAcres("12");
		farmData.setCropType("potato");
		farmData.setSeason("January");
		farmData.setPlantedCount(200);
		
		
		
		// Mocking out the vehicle service
		Mockito.when(farmServiceMock.findFarmByAcresCropTypeSeason(any(Farm.class))).thenReturn(farmData);
	
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/api/v1/harvested")
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content("{\"acres\": \"12\", \"cropType\": \"potato\", \"season\": \"February\", \"harvested\": \"10\"}");

		mockMvc.perform(builder).andExpect(status().isOk());
	}
	
//	@Test
//	void findTheGreatestFromAllData_OneValue() {
//		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{35});
//		assertEquals(35, businessImpl.findTheGreatestFromAllData());
//	}

}
