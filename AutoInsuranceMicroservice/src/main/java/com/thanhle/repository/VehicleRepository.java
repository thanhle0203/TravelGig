package com.thanhle.repository;

import com.thanhle.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

	Vehicle findByMake(String make);
	Vehicle findByMakeAndModel(String vehicleMake, String vehicleModel);
	
	List<Vehicle> findByModel(String model);
	List<Vehicle> findByYear(int year);
	
	@Query("SELECT DISTINCT v.make FROM Vehicle v")
    List<String> findAllMakes();
	
	@Query("SELECT DISTINCT v.model FROM Vehicle v")
	List<String> findByModels();
	
    @Query("SELECT DISTINCT v.model FROM Vehicle v WHERE v.make = :make")
    List<String> findModelsByMake(String make);

    @Query("SELECT DISTINCT v.year FROM Vehicle v")
    List<Integer> findAllYears();

    
    

    //Vehicle findByMakeAndModel(String vehicleMake, String vehicleModel);
}
