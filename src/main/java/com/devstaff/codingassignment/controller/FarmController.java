package com.devstaff.codingassignment.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.devstaff.codingassignment.entity.Farm;
import com.devstaff.codingassignment.service.FarmService;


@RestController
@RequestMapping("/api/v1/")
public class FarmController {
	
	@Autowired
	private FarmService farmService;
	
	@RequestMapping(value="/planted", method = RequestMethod.POST)
	public ResponseEntity<Farm> performFarmPlanted(@RequestBody Farm farm){
		Farm farmFromDb = farmService.findFarmByAcresCropTypeSeason(farm);
		if(null != farmFromDb) {
			farmFromDb.setUpdatedDate(new Date());
			farmFromDb.setCropType(farm.getCropType());
			farmFromDb.setPlantedCount(farm.getPlantedCount());
			farmFromDb.setCreationDate(new Date());
			farmFromDb.setPlantedCurrentCount(farm.getPlantedCount());
			farmService.save(farmFromDb);
			return new ResponseEntity<Farm>(farmFromDb, HttpStatus.OK);
		} else {
			Farm saveFarm = farmService.save(farm);
			return new ResponseEntity<Farm>(saveFarm, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/harvested", method = RequestMethod.PUT)
	public ResponseEntity<String> performFarmHarveste(@RequestBody Farm farm){
		Farm farmFromDb = farmService.findFarmByAcresCropTypeSeason(farm);
		if(null != farmFromDb) {
			farmFromDb.setUpdatedDate(new Date());
			farmFromDb.setCropType(farm.getCropType());
			int harvestAmount = farmFromDb.getHarvested();
			int totalHarvest = harvestAmount+farm.getHarvested(); 
			if(totalHarvest < farmFromDb.getPlantedCount()) {
				farmFromDb.setHarvested(totalHarvest);
			} else {
				return new ResponseEntity<>("Cannot harvest more than available "+farm.getAcres()+", "+farm.getCropType()+","+farm.getSeason(), HttpStatus.OK);
			}
			farmService.save(farmFromDb);
			return new ResponseEntity<>("Farm with "+farm.getAcres()+", "+farm.getCropType()+","+farm.getSeason()+" Updated", HttpStatus.OK);
		} else {
		    return new ResponseEntity<>("Failure: Cannot find farm with acres "+farm.getAcres()+" ", HttpStatus.OK);
		}
	}
}
