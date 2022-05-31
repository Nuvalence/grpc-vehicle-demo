package com.proto;

import static com.proto.VehicleServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: vehicle/vehicle.proto")
public final class ReactorVehicleServiceGrpc {
    private ReactorVehicleServiceGrpc() {}

    public static ReactorVehicleServiceStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorVehicleServiceStub(channel);
    }

    public static final class ReactorVehicleServiceStub extends io.grpc.stub.AbstractStub<ReactorVehicleServiceStub> {
        private VehicleServiceGrpc.VehicleServiceStub delegateStub;

        private ReactorVehicleServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = VehicleServiceGrpc.newStub(channel);
        }

        private ReactorVehicleServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = VehicleServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorVehicleServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorVehicleServiceStub(channel, callOptions);
        }

        public reactor.core.publisher.Mono<com.proto.Vehicle.AllMakesResponse> allMakes(reactor.core.publisher.Mono<com.proto.Vehicle.AllMakesRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::allMakes, getCallOptions());
        }

        public reactor.core.publisher.Flux<com.proto.Vehicle.AllMakesStreamResponse> allMakesStream(reactor.core.publisher.Mono<com.proto.Vehicle.AllMakesStreamRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToMany(reactorRequest, delegateStub::allMakesStream, getCallOptions());
        }

        public reactor.core.publisher.Mono<com.proto.Vehicle.ModelsForMakesResponse> modelsForMakes(reactor.core.publisher.Flux<com.proto.Vehicle.ModelsForMakesRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.manyToOne(reactorRequest, delegateStub::modelsForMakes, getCallOptions());
        }

        public reactor.core.publisher.Flux<com.proto.Vehicle.GetModelByMakeResponse> getModelByMake(reactor.core.publisher.Flux<com.proto.Vehicle.GetModelByMakeRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.manyToMany(reactorRequest, delegateStub::getModelByMake, getCallOptions());
        }

        public reactor.core.publisher.Mono<com.proto.Vehicle.AllMakesResponse> allMakes(com.proto.Vehicle.AllMakesRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::allMakes, getCallOptions());
        }

        public reactor.core.publisher.Flux<com.proto.Vehicle.AllMakesStreamResponse> allMakesStream(com.proto.Vehicle.AllMakesStreamRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToMany(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::allMakesStream, getCallOptions());
        }

    }

    public static abstract class VehicleServiceImplBase implements io.grpc.BindableService {

        public reactor.core.publisher.Mono<com.proto.Vehicle.AllMakesResponse> allMakes(reactor.core.publisher.Mono<com.proto.Vehicle.AllMakesRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Flux<com.proto.Vehicle.AllMakesStreamResponse> allMakesStream(reactor.core.publisher.Mono<com.proto.Vehicle.AllMakesStreamRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Mono<com.proto.Vehicle.ModelsForMakesResponse> modelsForMakes(reactor.core.publisher.Flux<com.proto.Vehicle.ModelsForMakesRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public reactor.core.publisher.Flux<com.proto.Vehicle.GetModelByMakeResponse> getModelByMake(reactor.core.publisher.Flux<com.proto.Vehicle.GetModelByMakeRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            com.proto.VehicleServiceGrpc.getAllMakesMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.proto.Vehicle.AllMakesRequest,
                                            com.proto.Vehicle.AllMakesResponse>(
                                            this, METHODID_ALL_MAKES)))
                    .addMethod(
                            com.proto.VehicleServiceGrpc.getAllMakesStreamMethod(),
                            asyncServerStreamingCall(
                                    new MethodHandlers<
                                            com.proto.Vehicle.AllMakesStreamRequest,
                                            com.proto.Vehicle.AllMakesStreamResponse>(
                                            this, METHODID_ALL_MAKES_STREAM)))
                    .addMethod(
                            com.proto.VehicleServiceGrpc.getModelsForMakesMethod(),
                            asyncClientStreamingCall(
                                    new MethodHandlers<
                                            com.proto.Vehicle.ModelsForMakesRequest,
                                            com.proto.Vehicle.ModelsForMakesResponse>(
                                            this, METHODID_MODELS_FOR_MAKES)))
                    .addMethod(
                            com.proto.VehicleServiceGrpc.getGetModelByMakeMethod(),
                            asyncBidiStreamingCall(
                                    new MethodHandlers<
                                            com.proto.Vehicle.GetModelByMakeRequest,
                                            com.proto.Vehicle.GetModelByMakeResponse>(
                                            this, METHODID_GET_MODEL_BY_MAKE)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

    }

    public static final int METHODID_ALL_MAKES = 0;
    public static final int METHODID_ALL_MAKES_STREAM = 1;
    public static final int METHODID_MODELS_FOR_MAKES = 2;
    public static final int METHODID_GET_MODEL_BY_MAKE = 3;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final VehicleServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(VehicleServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_ALL_MAKES:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((com.proto.Vehicle.AllMakesRequest) request,
                            (io.grpc.stub.StreamObserver<com.proto.Vehicle.AllMakesResponse>) responseObserver,
                            serviceImpl::allMakes);
                    break;
                case METHODID_ALL_MAKES_STREAM:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToMany((com.proto.Vehicle.AllMakesStreamRequest) request,
                            (io.grpc.stub.StreamObserver<com.proto.Vehicle.AllMakesStreamResponse>) responseObserver,
                            serviceImpl::allMakesStream);
                    break;
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_MODELS_FOR_MAKES:
                    return (io.grpc.stub.StreamObserver<Req>) com.salesforce.reactorgrpc.stub.ServerCalls.manyToOne(
                            (io.grpc.stub.StreamObserver<com.proto.Vehicle.ModelsForMakesResponse>) responseObserver,
                            serviceImpl::modelsForMakes, serviceImpl.getCallOptions(methodId));
                case METHODID_GET_MODEL_BY_MAKE:
                    return (io.grpc.stub.StreamObserver<Req>) com.salesforce.reactorgrpc.stub.ServerCalls.manyToMany(
                            (io.grpc.stub.StreamObserver<com.proto.Vehicle.GetModelByMakeResponse>) responseObserver,
                            serviceImpl::getModelByMake, serviceImpl.getCallOptions(methodId));
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }

}
