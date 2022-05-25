package com.nuvalence.grpcvehicledemo.repository;

import com.nuvalence.grpcvehicledemo.model.Vehicle;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;


public interface VehicleRepository extends ReactiveMongoRepository<Vehicle, String> {

    // custom query to search for vehicle details by model year (from 2019-2022)
    @Query("{ 'modelYear':  ?0}")
    Flux<Vehicle> findAllByModelYear(int modelYear);


}
