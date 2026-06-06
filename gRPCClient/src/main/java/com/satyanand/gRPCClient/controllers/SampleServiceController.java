package com.satyanand.gRPCClient.controllers;

import com.satyanand.gRPCClient.client.GrpcClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sample")
//@RequiredArgsConstructor
public class SampleServiceController {

    private final GrpcClient grpcClient;

    public SampleServiceController(GrpcClient grpcClient) {
        this.grpcClient = grpcClient;
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam String name){
        return grpcClient.sayHello(name);
    }

}
