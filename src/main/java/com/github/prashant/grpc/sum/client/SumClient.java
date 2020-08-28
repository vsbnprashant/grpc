package com.github.prashant.grpc.sum.client;

import com.proto.sum.Operands;
import com.proto.sum.SumRequest;
import com.proto.sum.SumResponse;
import com.proto.sum.SumServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 *
 * @author bns
 */
public class SumClient {


    public static void main(String args[]) {

        System.out.print("Hello I am a client");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).
                usePlaintext(). //disable ssl
                build();

        //synchronous client
        //old dummy code
        //DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(channel);

//create greet client
        SumServiceGrpc.SumServiceBlockingStub sumclient = SumServiceGrpc.newBlockingStub(channel);

        //create protocol buffer sum request
        Operands o = Operands.newBuilder().setFirstoperand("5").setSecondoperand("7").build();
        SumRequest sumRequest = SumRequest.newBuilder().setOperands(o).build();

        //create greet request
        //call the RPC and get response
        SumResponse sumResponse = sumclient.sum(sumRequest);

        System.out.println("Response  is " + sumResponse.getResult());

        System.out.println("Shutting down client");
        channel.shutdown();


    }
}
