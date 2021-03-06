package com.lbc.mo.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.examples.TestProto;
import io.grpc.examples.TestServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

public class GrpcServer {

    private static final Log LOG = LogFactory.getLog(GrpcServer.class);

    private Server server;

    private void start() throws IOException {
    /* The port on which the server should run */
        int port = 9099;
        server = ServerBuilder.forPort(port)
                .addService(new TestService())
                .build()
                .start();
        LOG.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                GrpcServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final GrpcServer server = new GrpcServer();
        server.start();
        server.blockUntilShutdown();
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    class TestService extends TestServiceGrpc.TestServiceImplBase {
        @Override
        public void sayHello(TestProto.TestRequest request, StreamObserver<TestProto.TestReply> responseObserver) {
            TestProto.TestReply.Builder builder = TestProto.TestReply.newBuilder();
            builder.setMessage("response");
            responseObserver.onNext(builder.build());
            responseObserver.onCompleted();
        }
    }
}
