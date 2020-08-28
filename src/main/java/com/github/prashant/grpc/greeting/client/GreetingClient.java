package com.github.prashant.grpc.greeting.client;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 *
 * @author bns
 */
public class GreetingClient {


    public static void main(String args[]) {

        System.out.print("Hello I am a client");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).
                usePlaintext(). //disable ssl
                build();

        //synchronous client
        //old dummy code
        //DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(channel);

//create greet client
        GreetServiceGrpc.GreetServiceBlockingStub greetclient = GreetServiceGrpc.newBlockingStub(channel);

        //create protocol buffer greeting message
        Greeting greeting = Greeting.newBuilder().setFirstName("Prash").setLastName("Sok").build();

        //create greet request
        GreetRequest greetRequest = GreetRequest.newBuilder().setGreeting(greeting).build();

        //call the RPC and get response
        GreetResponse greetResponse = greetclient.greet(greetRequest);

        System.out.println("Response  is " + greetResponse.getResult());

        System.out.println("Shutting down client");
        channel.shutdown();


    }
}
