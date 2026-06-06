package com.satyanand.gRPCClient.client;

import com.satyanand.gRPCClient.SampleRequest;
import com.satyanand.gRPCClient.SampleResponse;
import com.satyanand.gRPCClient.SampleServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GrpcClient {

    @Value("${grpc.server.port:9090}")
    private int grpcServerPort;

    @Value("${grpc.server.host:localhost}")
    private String grpcServerHost;

    private ManagedChannel channel;

    private SampleServiceGrpc.SampleServiceBlockingStub sampleServiceStub;

    @PostConstruct
    public void init(){
        channel = ManagedChannelBuilder.forAddress(grpcServerHost, grpcServerPort)
                .usePlaintext()
                .build();
        sampleServiceStub = SampleServiceGrpc.newBlockingStub(channel);
    }

    public String sayHello(String name){
        SampleRequest request = SampleRequest.newBuilder().setName(name).build();

        SampleResponse response = sampleServiceStub.saySampleHello(request); // make the grpc call

        return response.getMessage();
    }
}
