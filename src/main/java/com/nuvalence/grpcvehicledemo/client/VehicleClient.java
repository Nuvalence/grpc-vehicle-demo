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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class VehicleClient {

    @GrpcClient("VehicleService")
    private ReactorVehicleServiceGrpc.ReactorVehicleServiceStub stub;

    public static void main(String[] args) {
        // uncomment the 3 lines below to test locally via ServerBuilder (make sure VehicleServer is running)
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

    public Mono<List<List<String>>> modelsForMakes(List<String> makes) {
        Flux<Vehicle.ModelsForMakesRequest> request = Flux.fromIterable(makes)
                .map(make ->Vehicle.ModelsForMakesRequest.newBuilder().setMake(make).build()
        );
        return stub.modelsForMakes(request).map(item -> {
            List<List<String>> response = new ArrayList<>();
            item.getModelsForMakeList().listIterator().forEachRemaining(lit -> response.add(lit.getModelsList()));
            return response;
        });
    }

    public Flux<List<String>> getModelByMakeAndYear(List<String> makes) {
        // initialize request
        Flux<Vehicle.GetModelByMakeRequest> requestStream = Flux.fromStream(
                makes.stream()
                        .map(make-> Vehicle.GetModelByMakeRequest.newBuilder().setMake(make).build())
        );

        return stub.getModelByMake(requestStream)
                .map(
                Vehicle.GetModelByMakeResponse::getModelsList
                );
    }

    public void test() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50053)
                .usePlaintext()
                .build();

        allMakes(channel);
        allMakesStream(channel);
        modelsForMakes(channel);
        getModelByMakeAndYear(channel);

        System.out.println("Shutting down channel");
        channel.shutdown();
    }

    // the following functions (commented out in main) are for testing while running a server (via VehicleServer.java) and a client connection locally
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

    }
    public void getModelByMakeAndYear(ManagedChannel channel) {

        CountDownLatch latch = new CountDownLatch(1);
        VehicleServiceGrpc.VehicleServiceStub asyncStub = VehicleServiceGrpc.newStub(channel);
        StreamObserver<Vehicle.GetModelByMakeRequest> requestStreamObserver = asyncStub.getModelByMake(new StreamObserver<Vehicle.GetModelByMakeResponse>() {
            @Override
            public void onNext(Vehicle.GetModelByMakeResponse value) {
                System.out.println("Received value from server");
                System.out.println(value.getModelsList());
            }
            @Override
            public void onError(Throwable t) {
            }
            @Override
            public void onCompleted() {
                System.out.println("Server finished sending stuff");
                latch.countDown();
            }
        });

        requestStreamObserver.onNext(
                Vehicle.GetModelByMakeRequest.newBuilder()
                        .setMake("honda")
                        .build()
        );
        requestStreamObserver.onNext(
                Vehicle.GetModelByMakeRequest.newBuilder()
                        .setMake("tesla")
                        .build()
        );
        requestStreamObserver.onNext(
                Vehicle.GetModelByMakeRequest.newBuilder()
                        .setMake("ford")
                        .build()
        );
        requestStreamObserver.onNext(
                Vehicle.GetModelByMakeRequest.newBuilder()
                        .setMake("audi")
                        .build()
        );
        requestStreamObserver.onNext(
                Vehicle.GetModelByMakeRequest.newBuilder()
                        .setMake("hyundai")
                        .build()
        );
        requestStreamObserver.onNext(
                Vehicle.GetModelByMakeRequest.newBuilder()
                        .setMake("hyundai")
                        .build()
        );
        requestStreamObserver.onNext(
                Vehicle.GetModelByMakeRequest.newBuilder()
                        .setMake("kia")
                        .build()
        );

        requestStreamObserver.onCompleted();
        try {
            latch.await(3L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
