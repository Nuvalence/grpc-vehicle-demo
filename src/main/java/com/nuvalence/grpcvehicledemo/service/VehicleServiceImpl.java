package com.nuvalence.grpcvehicledemo.service;

import com.proto.ReactorVehicleServiceGrpc;
import com.proto.Vehicle;
import net.devh.boot.grpc.server.service.GrpcService;
import org.json.JSONArray;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@GrpcService
public class VehicleServiceImpl extends ReactorVehicleServiceGrpc.VehicleServiceImplBase {

    public WebClient webClient;

    @Override
    public Mono<Vehicle.AllMakesResponse> allMakes(Mono<Vehicle.AllMakesRequest> request) {

        JSONObject jsonObject;
        ArrayList<String> models = new ArrayList<>();

        this.webClient = WebClient.builder()
                .exchangeStrategies(
                        ExchangeStrategies.builder().codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(1000000)).build())
                .baseUrl("https://vpic.nhtsa.dot.gov/api/vehicles")
                .build();

        var externalResponse = webClient
                .get()
                .uri("/GetAllMakes?format=json")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class).log()
                .block();

        jsonObject = new JSONObject(externalResponse);

        jsonObject.keys().forEachRemaining(key -> {
            if (key.equals("Results")) {
                Object results = jsonObject.get(key);
                ((JSONArray) results).iterator().forEachRemaining(element -> {
                    ((JSONObject) element).keys().forEachRemaining(resultsKey -> {
                        if (resultsKey.equals("Make_Name")) {
                            models.add((String) ((JSONObject) element).get(resultsKey));
                        }
                    });
                });
            }
        });

        Vehicle.AllMakesResponse response = Vehicle.AllMakesResponse.newBuilder()
                .addAllMake(models)
                .build();

        return Mono.just(response);
    }

    @Override
    public Flux<Vehicle.AllMakesStreamResponse> allMakesStream(Mono<Vehicle.AllMakesStreamRequest> request) {

        ArrayList<String> res = new ArrayList<>();
        int BATCH_SIZE = request.block().getBatchSize();

        ArrayList<Vehicle.AllMakesStreamResponse> responseStream = new ArrayList<>();

        this.webClient = WebClient.builder()
                .exchangeStrategies(
                        ExchangeStrategies.builder().codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(1000000)).build())
                .baseUrl("https://vpic.nhtsa.dot.gov/api/vehicles")
                .build();

        var externalResponse = webClient
                .get()
                .uri("/GetAllMakes?format=json")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class).log()
                .block();

        JSONObject jsonObject = new JSONObject(externalResponse);

        jsonObject.keys().forEachRemaining(key -> {
            if (key.equals("Results")) {
                Object results = jsonObject.get(key);
                ((JSONArray) results).iterator().forEachRemaining(element -> {
                    ((JSONObject) element).keys().forEachRemaining(resultsKey -> {
                        if (resultsKey.equals("Make_Name")) {
                            if (res.size() == BATCH_SIZE) {
                                responseStream.add(Vehicle.AllMakesStreamResponse.newBuilder().addAllMake(res).build());
                                res.clear();
                            }
                            else if (res.size() < BATCH_SIZE) {
                                res.add((String)((JSONObject) element).get(resultsKey));
                                System.out.println(((JSONObject) element).get(resultsKey));
                            }
                        }
                    });
                });
            }
        });

        if (res.size() < BATCH_SIZE) {
            // making sure the last batch is processed (in case the next batch is smaller than the given batch size)
            responseStream.add(Vehicle.AllMakesStreamResponse.newBuilder().addAllMake(res).build());
        }

        return Flux.fromIterable(responseStream);
    }

    @Override
    public Mono<Vehicle.ModelsForMakesResponse> modelsForMakes(Flux<Vehicle.ModelsForMakesRequest> request) {

        this.webClient = WebClient.builder()
                .exchangeStrategies(
                        ExchangeStrategies.builder().codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(1000000)).build())
                .baseUrl("https://vpic.nhtsa.dot.gov/api/vehicles")
                .build();

        return request
                .map(item -> {
                    List<String> models = new ArrayList<>();
                    String make = item.getMake();
                    JSONObject modelJson = new JSONObject(webClient
                            .get()
                            .uri("/GetModelsForMake/"+make+"?format=json")
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(String.class).log()
                            .block());

                    modelJson.keys().forEachRemaining(key -> {
                        if (key.equals("Results")) {
                            Object results = modelJson.get(key);
                            ((JSONArray) results).iterator().forEachRemaining(element -> ((JSONObject) element).keys().forEachRemaining(resultsKey -> {
                                if (resultsKey.equals("Model_Name")) {
                                    models.add((String)((JSONObject) element).get(resultsKey));
                                }
                            }));
                        }
                    });
                    return Vehicle.ModelsForMake.newBuilder().addAllModels(models).build();
                })
                .collectList()
                .map(mono-> Vehicle.ModelsForMakesResponse.newBuilder().addAllModelsForMake(mono).build());
    }


    @Override
    public Flux<Vehicle.GetModelByMakeResponse> getModelByMake(Flux<Vehicle.GetModelByMakeRequest> request) {

        this.webClient = WebClient.builder()
                .exchangeStrategies(
                        ExchangeStrategies.builder().codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(1000000)).build())
                .baseUrl("https://vpic.nhtsa.dot.gov/api/vehicles")
                .build();

        return request.map(req -> {
                    ArrayList<String> models = new ArrayList<>();
                    String make = req.getMake();

                    JSONObject modelsJson = new JSONObject(webClient
                            .get()
                            .uri("/GetModelsForMake/"+make+"?format=json")
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(String.class).log()
                            .block());
//                    this.extractAndAddModels(modelsJson, models, "Model_Name", year+" "+make);
                    modelsJson.keys().forEachRemaining(key -> {
                        if (key.equals("Results")) {
                            Object results = modelsJson.get(key);
                            ((JSONArray) results).iterator().forEachRemaining(element -> {
                                ((JSONObject) element).keys().forEachRemaining(resultsKey -> {
                                    if (resultsKey.equals("Model_Name")) {
                                        if (make != null) {
                                            models.add(make.toUpperCase() +
                                                    " " + ((JSONObject) element).get(resultsKey));
                                        } else {
                                            models.add((String)((JSONObject) element).get(resultsKey));
                                        }
                                    }
                                });
                            });
                        }
                    });
                    return Vehicle.GetModelByMakeResponse.newBuilder().addAllModels(models).build() ;
                });
    }

    // json to extract
    // array to add to
    // field to extract by
    public void extractAndAddModels(JSONObject jsonObject, ArrayList<String> models, String desiredField, String prefix) {
        jsonObject.keys().forEachRemaining(key -> {
            if (key.equals("Results")) {
                Object results = jsonObject.get(key);
                ((JSONArray) results).iterator().forEachRemaining(element -> {
                    ((JSONObject) element).keys().forEachRemaining(resultsKey -> {
                        if (resultsKey.equals(desiredField)) {
                            if (prefix != null) {
                                models.add(prefix.toUpperCase() +
                                        " " + ((JSONObject) element).get(resultsKey));
                            } else {
                                models.add((String)((JSONObject) element).get(resultsKey));
                            }
                        }
                    });
                });
            }
        });
    }
}
