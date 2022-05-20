package com.nuvalence.grpcvehicledemo.client;

import com.proto.ReactorVehicleServiceGrpc;
import com.proto.Vehicle;
import com.proto.VehicleServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class VehicleClient {

    @GrpcClient("VehicleService")
    private ReactorVehicleServiceGrpc.ReactorVehicleServiceStub stub;

    public static void main(String[] args) {
//        System.out.println("This is the Vehicle Client starting");
//        VehicleClient main = new VehicleClient();
//        main.test();
        System.out.println("client is running");

    }

    public Mono<List<String>> allMakes() {
        Mono<Vehicle.AllMakesRequest> request = Mono.just(Vehicle.AllMakesRequest.newBuilder().build());
        return stub.allMakes(request).map(Vehicle.AllMakesResponse::getMakeList);
    }


    public Flux<List<String>> allMakesStream(int batchSize) {
        Mono<Vehicle.AllMakesStreamRequest> request = Mono.just(Vehicle.AllMakesStreamRequest.newBuilder().setBatchSize(batchSize).build());
        return stub.allMakesStream(request).map(
                Vehicle.AllMakesStreamResponse::getMakeList
        );
    }

    public Mono<List<Vehicle.ModelsForMake>> modelsForMakes(List<String> makes) {
        Flux<Vehicle.ModelsForMakesRequest> request = Flux.fromIterable(makes)
                .map(make ->Vehicle.ModelsForMakesRequest.newBuilder().setMake(make).build()
        );

        request.doOnNext(s->{
            System.out.println("Client on next");
            System.out.println(s.getMake());
        }).doOnComplete(()->{
            System.out.println("Client on complete");
        }).subscribe();

        return stub.modelsForMakes(request).doOnNext(s->{
            System.out.println("CLIENT SERVER THING ON NEXT");
            System.out.println(s.getModelsForMakeList());
        }).doOnSuccess((s)->{
            System.out.println("SUCCESS");
            System.out.println(s.getModelsForMakeList());
        }).map(s -> {
            System.out.println("CLIENT");
            System.out.println(s);
            return s.getModelsForMakeList();
        });

//        System.out.println("HERE");
//        var outerList= stub.modelsForMakes(request).map(Vehicle.ModelsForMakesResponse::getModelsForMakeList);
//
//        List<List<String>> re = new ArrayList<>();
//
//        System.out.println(outerList);
//        System.out.println("HERE2");
//        outerList.subscribe(modelsForMakes -> {
//            System.out.println("==========??");
//            System.out.println(modelsForMakes);
//            modelsForMakes.forEach(modelsForMake -> {
//                re.add(modelsForMake.getModelsList());
//            });
//        });
//        System.out.println("HERE3");
//        System.out.println(re);
//        return Mono.just(re);
    }

    public void modelsForMakes(ManagedChannel channel) {

        CountDownLatch latch = new CountDownLatch(1);

        VehicleServiceGrpc.VehicleServiceStub asyncStub = VehicleServiceGrpc.newStub(channel);

        StreamObserver<Vehicle.ModelsForMakesRequest> requestStreamObserver = asyncStub.modelsForMakes(new StreamObserver<Vehicle.ModelsForMakesResponse>() {
            @Override
            public void onNext(Vehicle.ModelsForMakesResponse value) {
                System.out.println("Server response");
                System.out.println(value.getModelsForMakeList());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                System.out.println("Server sent a response");
                latch.countDown();


            }
        });

        requestStreamObserver.onNext(
                Vehicle.ModelsForMakesRequest.newBuilder()
                        .setMake("honda")
                        .build()
        );
        requestStreamObserver.onNext(
                Vehicle.ModelsForMakesRequest.newBuilder()
                        .setMake("audi")
                        .build()
        );
        requestStreamObserver.onNext(
                Vehicle.ModelsForMakesRequest.newBuilder()
                        .setMake("tesla")
                        .build()
        );


        requestStreamObserver.onCompleted();

        try {
            latch.await(3L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        asyncStub.modelsForMakes(new Flux<>)

    }

    public Flux<List<String>> getModelByMakeAndYear( List<String> makes) {
        // initialize request
        Flux<Vehicle.GetModelByMakeAndYearRequest> requestStream = Flux.fromStream(
                makes.stream()
                        .map(make-> Vehicle.GetModelByMakeAndYearRequest.newBuilder().setMake(make).build())
        );

        return stub.getModelByMakeAndYear(requestStream)
                .map(
                Vehicle.GetModelByMakeAndYearResponse::getModelsList
                );
    }

//    public void getModelByMakeAndYear(ManagedChannel channel) {
//
//        CountDownLatch latch = new CountDownLatch(1);
//        VehicleServiceGrpc.VehicleServiceStub asyncStub = VehicleServiceGrpc.newStub(channel);
//
//        StreamObserver<Vehicle.GetModelByMakeAndYearRequest> requestStreamObserver = asyncStub.getModelByMakeAndYear(new StreamObserver<Vehicle.GetModelByMakeAndYearResponse>() {
//            @Override
//            public void onNext(Vehicle.GetModelByMakeAndYearResponse value) {
//                System.out.println("Received value from server");
//                System.out.println(value.getModelsList());
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
//                System.out.println("Server finished sending stuff");
//                latch.countDown();
//            }
//        });
//
//        requestStreamObserver.onNext(
//                Vehicle.GetModelByMakeAndYearRequest.newBuilder()
//                        .setMake("honda")
//                        .setYear(2020)
//                        .build()
//        );
//        requestStreamObserver.onNext(
//                Vehicle.GetModelByMakeAndYearRequest.newBuilder()
//                        .setMake("tesla")
//                        .setYear(2021)
//                        .build()
//        );
//        requestStreamObserver.onNext(
//                Vehicle.GetModelByMakeAndYearRequest.newBuilder()
//                        .setMake("ford")
//                        .setYear(2020)
//                        .build()
//        );
//        requestStreamObserver.onNext(
//                Vehicle.GetModelByMakeAndYearRequest.newBuilder()
//                        .setMake("audi")
//                        .setYear(2022)
//                        .build()
//        );
//        requestStreamObserver.onNext(
//                Vehicle.GetModelByMakeAndYearRequest.newBuilder()
//                        .setMake("hyundai")
//                        .setYear(2015)
//                        .build()
//        );
//        requestStreamObserver.onNext(
//                Vehicle.GetModelByMakeAndYearRequest.newBuilder()
//                        .setMake("hyundai")
//                        .setYear(1990)
//                        .build()
//        );
//        requestStreamObserver.onNext(
//                Vehicle.GetModelByMakeAndYearRequest.newBuilder()
//                        .setMake("kia")
//                        .setYear(1990)
//                        .build()
//        );
//
//
//
//        requestStreamObserver.onCompleted();
//        try {
//            latch.await(3L, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//
//    }


    public void test() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50053)
                .usePlaintext()
                .build();

        allMakes(channel);
        allMakesStream(channel);
        modelsForMakes(channel);
//        getModelByMakeAndYear(channel);

        System.out.println("Shutting down channel");
        channel.shutdown();
    }


    public void allMakes(ManagedChannel channel) {

        VehicleServiceGrpc.VehicleServiceBlockingStub stub = VehicleServiceGrpc.newBlockingStub(channel);

        Vehicle.AllMakesRequest request = Vehicle.AllMakesRequest.newBuilder()
                .build();

        Vehicle.AllMakesResponse response = stub.allMakes(request);
        System.out.println("Got response:");
        System.out.println("====");
        System.out.println(response.toString());
        System.out.println("====");
        System.out.println(response.getMakeList());
    }
    public void allMakesStream(ManagedChannel channel) {

        VehicleServiceGrpc.VehicleServiceBlockingStub stub = VehicleServiceGrpc.newBlockingStub(channel);

        Vehicle.AllMakesStreamRequest request = Vehicle.AllMakesStreamRequest.newBuilder()
                .setBatchSize(1000)
                .build();

        stub.allMakesStream(request)
                .forEachRemaining(allMakesStreamResponse -> {
                    System.out.println(allMakesStreamResponse.getMakeList());
                });

        System.out.println("Got response:");
        System.out.println("====");
    }

}
