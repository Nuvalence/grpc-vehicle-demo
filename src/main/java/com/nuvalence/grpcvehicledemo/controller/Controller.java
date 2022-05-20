package com.nuvalence.grpcvehicledemo.controller;

import com.nuvalence.grpcvehicledemo.client.VehicleClient;
import com.proto.Vehicle;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class Controller {

    private final VehicleClient vehicleClient;

    public Controller(VehicleClient vehicleClient) { this.vehicleClient = vehicleClient; }

    @GetMapping(value = "/allMakes")
    public Mono<List<String>> getAllMakes() {
        return vehicleClient.allMakes();
    }

    @GetMapping(value = "/allMakesStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<List<String>> getAllMakes(@RequestParam(value = "batchSize") int batchSize) {
        return vehicleClient.allMakesStream(batchSize);
    }

    @GetMapping(value = "/models")
    public Mono<List<Vehicle.ModelsForMake>> getModels(@RequestParam(value = "makes") List<String> makes) {
        return vehicleClient.modelsForMakes(makes);
    }


    @GetMapping(value = "/model", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<List<String>> getModelStream(@RequestParam(value = "makes") List<String> makes) {
        return vehicleClient.getModelByMakeAndYear(makes);
    }





}
