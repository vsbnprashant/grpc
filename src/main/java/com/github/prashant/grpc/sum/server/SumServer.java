package com.github.prashant.grpc.sum.server;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 *
 * @author bns
 */
public class SumServer {


    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello GRPC Summer");
        Server server = ServerBuilder.forPort(50051).
                addService(new SumServiceImpl()).build();
        server.start();


        //whenever our java app receives shutdown request, shutdown service also
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.print("Received shutdown request..");
            server.shutdown();
            System.out.print(" shutdown complete");

        }));

        server.awaitTermination();

    }
}
