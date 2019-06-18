package com.lbc.mo.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.examples.TestProto;
import io.grpc.examples.TestServiceGrpc;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class TestClient {
    private static final Logger logger = Logger.getLogger(TestClient.class.getName());
    private final ManagedChannel channel;
    private final TestServiceGrpc.TestServiceBlockingStub blockingStub;

    public TestClient(String host, Integer port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext().build());
    }

    public TestClient(ManagedChannel channel) {
        this.channel = channel;
        this.blockingStub = TestServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        TestClient testClient = new TestClient("localhost", 9099);
        TestProto.TestRequest changlb1 = TestProto.TestRequest.newBuilder().setName("changlb1").build();
        TestProto.TestReply testReply = testClient.blockingStub.sayHello(changlb1);
        System.out.println(testReply.getMessage());
        try {
            testClient.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
