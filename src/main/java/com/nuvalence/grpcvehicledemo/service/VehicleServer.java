package com.nuvalence.grpcvehicledemo.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class VehicleServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Starting Vehicle Server");
        Server server = ServerBuilder.forPort(50053)
                .addService(new VehicleServiceImpl())
                .build()
                .start();

        Runtime.getRuntime().addShutdownHook(new Thread( () -> {
            System.out.println("Received Shutdown Request");
            server.shutdown();
            System.out.println("Successfully stopped the server");
        }));

        server.awaitTermination();
    }
}
