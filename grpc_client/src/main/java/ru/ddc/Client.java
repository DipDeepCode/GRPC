package ru.ddc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

public class Client {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext()
                .build();
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        GreetingServiceOuterClass.HelloRequest request = GreetingServiceOuterClass.HelloRequest
                .newBuilder().setName("ClientName")
                .build();
        Iterator<GreetingServiceOuterClass.HelloResponse> response = stub.greeting(request);
        while (response.hasNext()) {
            System.out.println(response.next());
        }
        channel.shutdownNow();
    }
}
