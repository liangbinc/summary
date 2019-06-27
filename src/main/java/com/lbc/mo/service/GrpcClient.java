package com.lbc.mo.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.examples.TestProto;
import io.grpc.examples.TestServiceGrpc;

import java.util.concurrent.TimeUnit;

public class GrpcClient {
    private final ManagedChannel channel;
    private final TestServiceGrpc.TestServiceBlockingStub blockingStub;

    public GrpcClient(String host, Integer port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext().build());
    }

    public GrpcClient(ManagedChannel channel) {
        this.channel = channel;
        this.blockingStub = TestServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        GrpcClient testClient = new GrpcClient("localhost", 9099);
        TestProto.TestRequest changlb1 = TestProto.TestRequest.newBuilder().setName("changlb").build();
        TestProto.TestReply testReply = testClient.blockingStub.sayHello(changlb1);
        System.out.println(testReply.getMessage());
        try {
            testClient.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
