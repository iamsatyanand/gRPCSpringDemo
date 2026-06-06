package com.satyanand.gRPCServer.configs;

import com.satyanand.gRPCServer.services.SampleServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GrpcServerConfig {

    @Value("${grpc.server.port:9090}")
    private int grpcServerPort;
    private Server server;

    private final SampleServiceImpl sampleService;

    public GrpcServerConfig(){
        this.sampleService = new SampleServiceImpl();
    }


    @PostConstruct
    public void startGrpcServer() throws IOException{
        server = ServerBuilder
                .forPort(grpcServerPort)
                .addService(sampleService)
                .build()
                .start();

        System.out.println("gRPC server started on port :"+ grpcServerPort);

        new Thread(() -> {
            try{
                if(server != null){
                    server.awaitTermination();
                }
            }catch (InterruptedException exception){
                Thread.currentThread().interrupt();
                System.out.println("gRPC server interrupted");
            }
        }).start();

        Runtime.getRuntime().addShutdownHook(new Thread(() ->{
            System.out.println("Shutting down gRPC server...");
            if(server != null){
                server.shutdown();
            }
        }));
    }
}
