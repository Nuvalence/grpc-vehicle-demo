package com.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.45.1)",
    comments = "Source: vehicle/vehicle.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class VehicleServiceGrpc {

  private VehicleServiceGrpc() {}

  public static final String SERVICE_NAME = "vehicle.VehicleService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.proto.Vehicle.AllMakesRequest,
      com.proto.Vehicle.AllMakesResponse> getAllMakesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AllMakes",
      requestType = com.proto.Vehicle.AllMakesRequest.class,
      responseType = com.proto.Vehicle.AllMakesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.proto.Vehicle.AllMakesRequest,
      com.proto.Vehicle.AllMakesResponse> getAllMakesMethod() {
    io.grpc.MethodDescriptor<com.proto.Vehicle.AllMakesRequest, com.proto.Vehicle.AllMakesResponse> getAllMakesMethod;
    if ((getAllMakesMethod = VehicleServiceGrpc.getAllMakesMethod) == null) {
      synchronized (VehicleServiceGrpc.class) {
        if ((getAllMakesMethod = VehicleServiceGrpc.getAllMakesMethod) == null) {
          VehicleServiceGrpc.getAllMakesMethod = getAllMakesMethod =
              io.grpc.MethodDescriptor.<com.proto.Vehicle.AllMakesRequest, com.proto.Vehicle.AllMakesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AllMakes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.Vehicle.AllMakesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.Vehicle.AllMakesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new VehicleServiceMethodDescriptorSupplier("AllMakes"))
              .build();
        }
      }
    }
    return getAllMakesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.proto.Vehicle.AllMakesStreamRequest,
      com.proto.Vehicle.AllMakesStreamResponse> getAllMakesStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AllMakesStream",
      requestType = com.proto.Vehicle.AllMakesStreamRequest.class,
      responseType = com.proto.Vehicle.AllMakesStreamResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.proto.Vehicle.AllMakesStreamRequest,
      com.proto.Vehicle.AllMakesStreamResponse> getAllMakesStreamMethod() {
    io.grpc.MethodDescriptor<com.proto.Vehicle.AllMakesStreamRequest, com.proto.Vehicle.AllMakesStreamResponse> getAllMakesStreamMethod;
    if ((getAllMakesStreamMethod = VehicleServiceGrpc.getAllMakesStreamMethod) == null) {
      synchronized (VehicleServiceGrpc.class) {
        if ((getAllMakesStreamMethod = VehicleServiceGrpc.getAllMakesStreamMethod) == null) {
          VehicleServiceGrpc.getAllMakesStreamMethod = getAllMakesStreamMethod =
              io.grpc.MethodDescriptor.<com.proto.Vehicle.AllMakesStreamRequest, com.proto.Vehicle.AllMakesStreamResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AllMakesStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.Vehicle.AllMakesStreamRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.Vehicle.AllMakesStreamResponse.getDefaultInstance()))
              .setSchemaDescriptor(new VehicleServiceMethodDescriptorSupplier("AllMakesStream"))
              .build();
        }
      }
    }
    return getAllMakesStreamMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.proto.Vehicle.ModelsForMakesRequest,
      com.proto.Vehicle.ModelsForMakesResponse> getModelsForMakesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ModelsForMakes",
      requestType = com.proto.Vehicle.ModelsForMakesRequest.class,
      responseType = com.proto.Vehicle.ModelsForMakesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.proto.Vehicle.ModelsForMakesRequest,
      com.proto.Vehicle.ModelsForMakesResponse> getModelsForMakesMethod() {
    io.grpc.MethodDescriptor<com.proto.Vehicle.ModelsForMakesRequest, com.proto.Vehicle.ModelsForMakesResponse> getModelsForMakesMethod;
    if ((getModelsForMakesMethod = VehicleServiceGrpc.getModelsForMakesMethod) == null) {
      synchronized (VehicleServiceGrpc.class) {
        if ((getModelsForMakesMethod = VehicleServiceGrpc.getModelsForMakesMethod) == null) {
          VehicleServiceGrpc.getModelsForMakesMethod = getModelsForMakesMethod =
              io.grpc.MethodDescriptor.<com.proto.Vehicle.ModelsForMakesRequest, com.proto.Vehicle.ModelsForMakesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ModelsForMakes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.Vehicle.ModelsForMakesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.Vehicle.ModelsForMakesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new VehicleServiceMethodDescriptorSupplier("ModelsForMakes"))
              .build();
        }
      }
    }
    return getModelsForMakesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.proto.Vehicle.GetModelByMakeAndYearRequest,
      com.proto.Vehicle.GetModelByMakeAndYearResponse> getGetModelByMakeAndYearMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetModelByMakeAndYear",
      requestType = com.proto.Vehicle.GetModelByMakeAndYearRequest.class,
      responseType = com.proto.Vehicle.GetModelByMakeAndYearResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.proto.Vehicle.GetModelByMakeAndYearRequest,
      com.proto.Vehicle.GetModelByMakeAndYearResponse> getGetModelByMakeAndYearMethod() {
    io.grpc.MethodDescriptor<com.proto.Vehicle.GetModelByMakeAndYearRequest, com.proto.Vehicle.GetModelByMakeAndYearResponse> getGetModelByMakeAndYearMethod;
    if ((getGetModelByMakeAndYearMethod = VehicleServiceGrpc.getGetModelByMakeAndYearMethod) == null) {
      synchronized (VehicleServiceGrpc.class) {
        if ((getGetModelByMakeAndYearMethod = VehicleServiceGrpc.getGetModelByMakeAndYearMethod) == null) {
          VehicleServiceGrpc.getGetModelByMakeAndYearMethod = getGetModelByMakeAndYearMethod =
              io.grpc.MethodDescriptor.<com.proto.Vehicle.GetModelByMakeAndYearRequest, com.proto.Vehicle.GetModelByMakeAndYearResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetModelByMakeAndYear"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.Vehicle.GetModelByMakeAndYearRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.Vehicle.GetModelByMakeAndYearResponse.getDefaultInstance()))
              .setSchemaDescriptor(new VehicleServiceMethodDescriptorSupplier("GetModelByMakeAndYear"))
              .build();
        }
      }
    }
    return getGetModelByMakeAndYearMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static VehicleServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<VehicleServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<VehicleServiceStub>() {
        @java.lang.Override
        public VehicleServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new VehicleServiceStub(channel, callOptions);
        }
      };
    return VehicleServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static VehicleServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<VehicleServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<VehicleServiceBlockingStub>() {
        @java.lang.Override
        public VehicleServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new VehicleServiceBlockingStub(channel, callOptions);
        }
      };
    return VehicleServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static VehicleServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<VehicleServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<VehicleServiceFutureStub>() {
        @java.lang.Override
        public VehicleServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new VehicleServiceFutureStub(channel, callOptions);
        }
      };
    return VehicleServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class VehicleServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void allMakes(com.proto.Vehicle.AllMakesRequest request,
        io.grpc.stub.StreamObserver<com.proto.Vehicle.AllMakesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAllMakesMethod(), responseObserver);
    }

    /**
     */
    public void allMakesStream(com.proto.Vehicle.AllMakesStreamRequest request,
        io.grpc.stub.StreamObserver<com.proto.Vehicle.AllMakesStreamResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAllMakesStreamMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.proto.Vehicle.ModelsForMakesRequest> modelsForMakes(
        io.grpc.stub.StreamObserver<com.proto.Vehicle.ModelsForMakesResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getModelsForMakesMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.proto.Vehicle.GetModelByMakeAndYearRequest> getModelByMakeAndYear(
        io.grpc.stub.StreamObserver<com.proto.Vehicle.GetModelByMakeAndYearResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getGetModelByMakeAndYearMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAllMakesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.proto.Vehicle.AllMakesRequest,
                com.proto.Vehicle.AllMakesResponse>(
                  this, METHODID_ALL_MAKES)))
          .addMethod(
            getAllMakesStreamMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                com.proto.Vehicle.AllMakesStreamRequest,
                com.proto.Vehicle.AllMakesStreamResponse>(
                  this, METHODID_ALL_MAKES_STREAM)))
          .addMethod(
            getModelsForMakesMethod(),
            io.grpc.stub.ServerCalls.asyncClientStreamingCall(
              new MethodHandlers<
                com.proto.Vehicle.ModelsForMakesRequest,
                com.proto.Vehicle.ModelsForMakesResponse>(
                  this, METHODID_MODELS_FOR_MAKES)))
          .addMethod(
            getGetModelByMakeAndYearMethod(),
            io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
              new MethodHandlers<
                com.proto.Vehicle.GetModelByMakeAndYearRequest,
                com.proto.Vehicle.GetModelByMakeAndYearResponse>(
                  this, METHODID_GET_MODEL_BY_MAKE_AND_YEAR)))
          .build();
    }
  }

  /**
   */
  public static final class VehicleServiceStub extends io.grpc.stub.AbstractAsyncStub<VehicleServiceStub> {
    private VehicleServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected VehicleServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new VehicleServiceStub(channel, callOptions);
    }

    /**
     */
    public void allMakes(com.proto.Vehicle.AllMakesRequest request,
        io.grpc.stub.StreamObserver<com.proto.Vehicle.AllMakesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAllMakesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void allMakesStream(com.proto.Vehicle.AllMakesStreamRequest request,
        io.grpc.stub.StreamObserver<com.proto.Vehicle.AllMakesStreamResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getAllMakesStreamMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.proto.Vehicle.ModelsForMakesRequest> modelsForMakes(
        io.grpc.stub.StreamObserver<com.proto.Vehicle.ModelsForMakesResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getModelsForMakesMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.proto.Vehicle.GetModelByMakeAndYearRequest> getModelByMakeAndYear(
        io.grpc.stub.StreamObserver<com.proto.Vehicle.GetModelByMakeAndYearResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getGetModelByMakeAndYearMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class VehicleServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<VehicleServiceBlockingStub> {
    private VehicleServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected VehicleServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new VehicleServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.proto.Vehicle.AllMakesResponse allMakes(com.proto.Vehicle.AllMakesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAllMakesMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.proto.Vehicle.AllMakesStreamResponse> allMakesStream(
        com.proto.Vehicle.AllMakesStreamRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getAllMakesStreamMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class VehicleServiceFutureStub extends io.grpc.stub.AbstractFutureStub<VehicleServiceFutureStub> {
    private VehicleServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected VehicleServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new VehicleServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.proto.Vehicle.AllMakesResponse> allMakes(
        com.proto.Vehicle.AllMakesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAllMakesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ALL_MAKES = 0;
  private static final int METHODID_ALL_MAKES_STREAM = 1;
  private static final int METHODID_MODELS_FOR_MAKES = 2;
  private static final int METHODID_GET_MODEL_BY_MAKE_AND_YEAR = 3;

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
          serviceImpl.allMakes((com.proto.Vehicle.AllMakesRequest) request,
              (io.grpc.stub.StreamObserver<com.proto.Vehicle.AllMakesResponse>) responseObserver);
          break;
        case METHODID_ALL_MAKES_STREAM:
          serviceImpl.allMakesStream((com.proto.Vehicle.AllMakesStreamRequest) request,
              (io.grpc.stub.StreamObserver<com.proto.Vehicle.AllMakesStreamResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_MODELS_FOR_MAKES:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.modelsForMakes(
              (io.grpc.stub.StreamObserver<com.proto.Vehicle.ModelsForMakesResponse>) responseObserver);
        case METHODID_GET_MODEL_BY_MAKE_AND_YEAR:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getModelByMakeAndYear(
              (io.grpc.stub.StreamObserver<com.proto.Vehicle.GetModelByMakeAndYearResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class VehicleServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    VehicleServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.proto.Vehicle.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("VehicleService");
    }
  }

  private static final class VehicleServiceFileDescriptorSupplier
      extends VehicleServiceBaseDescriptorSupplier {
    VehicleServiceFileDescriptorSupplier() {}
  }

  private static final class VehicleServiceMethodDescriptorSupplier
      extends VehicleServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    VehicleServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (VehicleServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new VehicleServiceFileDescriptorSupplier())
              .addMethod(getAllMakesMethod())
              .addMethod(getAllMakesStreamMethod())
              .addMethod(getModelsForMakesMethod())
              .addMethod(getGetModelByMakeAndYearMethod())
              .build();
        }
      }
    }
    return result;
  }
}
