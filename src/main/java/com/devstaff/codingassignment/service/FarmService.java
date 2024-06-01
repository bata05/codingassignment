package com.devstaff.codingassignment.service;

import java.util.List;
import com.devstaff.codingassignment.entity.Farm;

public interface FarmService {
	List<Farm> getAllFarm();
	Farm save(Farm farm);
	Farm findFarmByAcresCropTypeSeason(Farm farm);
}
