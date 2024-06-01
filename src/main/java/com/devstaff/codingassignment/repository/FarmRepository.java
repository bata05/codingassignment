package com.devstaff.codingassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devstaff.codingassignment.entity.Farm;

public interface FarmRepository extends JpaRepository<Farm, Long>{
	@Query("SELECT fr FROM Farm fr WHERE fr.acres = ?1 and fr.cropType=?2 and fr.season=?3")
	Farm findFarm(String farmName, String cropType, String season);
}
