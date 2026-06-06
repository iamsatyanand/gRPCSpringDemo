package com.satyanand.gRPCServer.services;

import com.satyanand.gRPCServer.SampleRequest;
import com.satyanand.gRPCServer.SampleResponse;
import com.satyanand.gRPCServer.SampleServiceGrpc;
import io.grpc.stub.StreamObserver;

public class SampleServiceImpl extends SampleServiceGrpc.SampleServiceImplBase {

    @Override
    public void saySampleHello(SampleRequest request, StreamObserver<SampleResponse> responseObserver) {
        String name = request.getName();
        String message = "Hello, "+ name + "; Welcome to the sample gRPC service";

        SampleResponse response = SampleResponse.newBuilder().setMessage(message).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
