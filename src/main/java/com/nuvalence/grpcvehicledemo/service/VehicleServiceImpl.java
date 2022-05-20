package com.nuvalence.grpcvehicledemo.service;

import com.proto.ReactorVehicleServiceGrpc;
import com.proto.Vehicle;
import net.devh.boot.grpc.server.service.GrpcService;
import org.json.JSONArray;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

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
            responseStream.add(Vehicle.AllMakesStreamResponse.newBuilder().addAllMake(res).build());
        }


        return Flux.fromIterable(responseStream);

    }


    @Override
    public Mono<Vehicle.ModelsForMakesResponse> modelsForMakes(Flux<Vehicle.ModelsForMakesRequest> request) {

        ArrayList<Vehicle.ModelsForMake> res = new ArrayList<>();

        this.webClient = WebClient.builder()
                .exchangeStrategies(
                        ExchangeStrategies.builder().codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(1000000)).build())
                .baseUrl("https://vpic.nhtsa.dot.gov/api/vehicles")
                .build();

//        System.out.println("CARS/");
//        var cars = request.collectList();
//        System.out.println(cars);
//        cars.block().forEach(car -> {
//            ArrayList<String> models = new ArrayList<>();
//            String make = car.getMake();
//            System.out.println(make);
//            var externalResponse = webClient
//                    .get()
//                    .uri("/GetModelsForMake/"+make+"?format=json")
//                    .accept(MediaType.APPLICATION_JSON)
//                    .retrieve()
//                    .bodyToMono(String.class).log()
//                    .block();
//            JSONObject jsonObject = new JSONObject(externalResponse);
//            extractAndAddModels(jsonObject, models, "Model_Name", make);
//
//            Vehicle.ModelsForMake modelsForMake = Vehicle.ModelsForMake.newBuilder()
//                    .addAllModels(models)
//                    .build();
//
//            System.out.println("creating Models for make2");
//
//            res.add(modelsForMake);
//
//            System.out.println("res");
//            System.out.println(res);
//        });
//        Vehicle.ModelsForMakesResponse response = Vehicle.ModelsForMakesResponse.newBuilder()
//                .addAllModelsForMake(res).build();
//        System.out.println(res);
//        System.out.println(response);
//        System.out.println(response.getModelsForMakeList());
//
//        return Mono.just(response);



        System.out.println("SERVICE");
        var x=request.doOnNext(modelsForMakesRequest -> {
                        ArrayList<String> models = new ArrayList<>();
                        String make = modelsForMakesRequest.getMake();
                        System.out.println("%%%");
                        System.out.println(make);
                        var externalResponse = webClient
                                .get()
                                .uri("/GetModelsForMake/"+make+"?format=json")
                                .accept(MediaType.APPLICATION_JSON)
                                .retrieve()
                                .bodyToMono(String.class).log()
                                .block();
                        JSONObject jsonObject = new JSONObject(externalResponse);

//                            extractAndAddModels(jsonObject, models, "Model_Name", make);
                                jsonObject.keys().forEachRemaining(key -> {
                                    if (key.equals("Results")) {
                                        Object results = jsonObject.get(key);
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


                        Vehicle.ModelsForMake modelsForMake = Vehicle.ModelsForMake.newBuilder()
                                .addAllModels(models)
                                .build();

                        System.out.println("creating Models for make");

                        res.add(modelsForMake);

                        System.out.println("res");
                        System.out.println(res);
                    })
                .doOnTerminate(()->{
                    System.out.println("TERMINATDO");
                })
                    .doOnComplete(()->{
                        System.out.println("Complete!");

                        Vehicle.ModelsForMakesResponse response = Vehicle.ModelsForMakesResponse.newBuilder().addAllModelsForMake(res).build();
                        System.out.println(res);
                        System.out.println(response);
                        System.out.println(response.getModelsForMakeList());
                    })
                .subscribe(s -> {
                    System.out.println("SUBSCRITTION");
                    System.out.println(s.getMake());
                });
//                .map( s->{
//                        System.out.println("SS");
//                        System.out.println(s);
//                        return (Vehicle.ModelsForMakesResponse.newBuilder().addAllModelsForMake(res).build());
//                    }).next();


            System.out.println("======@@@====");
            System.out.println(res);
//            System.out.println(response);

            return Mono.just((Vehicle.ModelsForMakesResponse.newBuilder().addAllModelsForMake(res).build()));

//return x;

//        return Mono.just(response);
//        StreamObserver<Vehicle.ModelsForMakesRequest> requestStreamObserver = new StreamObserver<Vehicle.ModelsForMakesRequest>() {
//            @Override
//            public void onNext(Vehicle.ModelsForMakesRequest value) {
//                ArrayList<String> models = new ArrayList<>();
//                String make = value.getMake();
//                var externalResponse = webClient
//                        .get()
//                        .uri("/GetModelsForMake/"+make+"?format=json")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .retrieve()
//                        .bodyToMono(String.class).log()
//                        .block();
//                JSONObject jsonObject = new JSONObject(externalResponse);
//
//                extractAndAddModels(jsonObject, models, "Model_Name", make);
//
//
//                Vehicle.ModelsForMake modelsForMake = Vehicle.ModelsForMake.newBuilder()
//                        .addAllModels(models)
//                        .build();
//
//                res.add(modelsForMake);
//            }
//
//            @Override
//            public void onError(Throwable t) {
//
//            }

//            @Override
//            public void onCompleted() {
//                System.out.println("Stream finished");
//                responseObserver.onNext(response);
//                responseObserver.onCompleted();
//            }
//        };

    }

    @Override
    public Flux<Vehicle.GetModelByMakeAndYearResponse> getModelByMakeAndYear(Flux<Vehicle.GetModelByMakeAndYearRequest> request) {

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
//                    responseStream.add(Vehicle.GetModelByMakeAndYearResponse.newBuilder().addAllModels(models).build());

                    return Vehicle.GetModelByMakeAndYearResponse.newBuilder().addAllModels(models).build() ;
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


//    @Override
//    public StreamObserver<Vehicle.GetModelByMakeAndYearRequest> getModelByMakeAndYear(StreamObserver<Vehicle.GetModelByMakeAndYearResponse> responseObserver) {
//        this.webClient = WebClient.builder()
//                .exchangeStrategies(
//                        ExchangeStrategies.builder().codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(1000000)).build())
//                .baseUrl("https://vpic.nhtsa.dot.gov/api/vehicles")
//                .build();
//
//        return new StreamObserver<>() {
//            @Override
//            public void onNext(Vehicle.GetModelByMakeAndYearRequest value) {
//                ArrayList<String> models = new ArrayList<>();
//                String make = value.getMake();
//                int year = value.getYear();
//
//                System.out.println("Finding models for: \n"+year+ " " + make.toUpperCase() +"'s");
//
//                JSONObject modelsJson = new JSONObject(webClient
//                        .get()
//                        .uri("/GetModelsForMakeYear/make/"+make+"/modelyear/"+year+"?format=json")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .retrieve()
//                        .bodyToMono(String.class).log()
//                        .block());
//
//                extractAndAddModels(modelsJson, models, "Model_Name", year+" "+make);
//                Vehicle.GetModelByMakeAndYearResponse resp = Vehicle.GetModelByMakeAndYearResponse.newBuilder()
//                        .addAllModels(models)
//                        .build();
//                responseObserver.onNext(resp);
//
//            }
//
//            @Override
//            public void onError(Throwable t) {
//
//            }
//
//            @Override
//            public void onCompleted() {
//                System.out.println("Server side completed!");
////                Vehicle.GetModelByMakeAndYearResponse resp = Vehicle.GetModelByMakeAndYearResponse.newBuilder()
////                        .addAllModels(models)
////                        .build();
////                responseObserver.onNext(resp);
//                responseObserver.onCompleted();
//            }
//        };
//
//
//
////        Flux s = new Flux<>() {
////            @Override
////            public void subscribe(CoreSubscriber<? super Object> actual) {
////                System.out.println("Where ma i");
////                actual.onComplete();
////            }
////
////
////        };
////
////        s.subscribe(z -> {
////            System.out.println(z);
////        });
////
////        return s;
////
////        Vehicle.GetModelByMakeAndYearResponse resp = Vehicle.GetModelByMakeAndYearResponse.newBuilder() .setModels().build();
////        responseObserver.onNext(resp);
////        return null;
//    }
}
