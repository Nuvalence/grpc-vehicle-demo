package com.nuvalence.grpcvehicledemo.controller;

import com.nuvalence.grpcvehicledemo.client.VehicleClient;
import com.nuvalence.grpcvehicledemo.repository.VehicleRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class Controller {

    private final VehicleClient vehicleClient;

    private VehicleRepository repository;

    public Controller(VehicleClient vehicleClient, VehicleRepository repository) {
        this.vehicleClient = vehicleClient;
        this.repository = repository;
    }

    @GetMapping
    public Flux<com.nuvalence.grpcvehicledemo.model.Vehicle> getAllVehicles() {
        // returns detailed info about vehicles like fuel consumptions, transmission, and drive descriptions as well
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<com.nuvalence.grpcvehicledemo.model.Vehicle>> getVehicle(@PathVariable String id) {
        return repository.findById(id)
                .map(product -> ResponseEntity.ok(product))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/details/{year}")
    public Flux<com.nuvalence.grpcvehicledemo.model.Vehicle> getVehicleInfoByYear(@PathVariable int year) {
        return repository.findAllByModelYear(year);
    }

    @GetMapping(value = "/allMakes")
    public Mono<List<String>> getAllMakes() {
        return vehicleClient.allMakes();
    }

    @GetMapping(value = "/allMakesStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<List<String>> getAllMakes(@RequestParam(value = "batchSize") int batchSize) {
        return vehicleClient.allMakesStream(batchSize);
    }


    // takes in a list of car makes to return a list containing a list of models
    @GetMapping(value = "/models")
    public Mono<List<List<String>>> getModels(@RequestParam(value = "makes") List<String> makes) {
        return vehicleClient.modelsForMakes(makes);
    }

    @GetMapping(value = "/model", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<List<String>> getModelStream(@RequestParam(value = "makes") List<String> makes) {
        return vehicleClient.modelsByMake(makes);
    }

}
