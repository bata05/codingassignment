package com.devstaff.codingassignment.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.devstaff.codingassignment.entity.Farm;
import com.devstaff.codingassignment.repository.FarmRepository;

@Service
public class FarmServiceImpl implements FarmService {
	
	@Autowired
	private FarmRepository farmRepository;
	
	public Farm save(Farm farm) {
		return farmRepository.save(farm);
	}

	public List<Farm> getAllFarm() {
		return farmRepository.findAll();
	}
	
	public Optional<Farm> findFarm(Long farmId) {
		return farmRepository.findById(farmId);
	}
	
	public Farm findFarmByAcresCropTypeSeason(Farm farm) {
		return farmRepository.findFarm(farm.getAcres(), farm.getCropType(), farm.getSeason());
	}

}
