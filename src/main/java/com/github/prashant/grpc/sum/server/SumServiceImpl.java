package com.github.prashant.grpc.sum.server;

import com.proto.sum.Operands;
import com.proto.sum.SumRequest;
import com.proto.sum.SumResponse;
import com.proto.sum.SumServiceGrpc;

import io.grpc.stub.StreamObserver;

/**
 *
 * @author bns
 */
public class SumServiceImpl extends SumServiceGrpc.SumServiceImplBase {

    @Override
    public void sum(SumRequest request, StreamObserver<SumResponse> responseObserver) {
        //  super.sum(request, responseObserver);
        Operands operands = request.getOperands();
        Integer a1 = Integer.valueOf(operands.getFirstoperand());
        Integer a2 = Integer.valueOf(operands.getSecondoperand());
        Integer a3 = a1 + a2;

        SumResponse response = SumResponse.newBuilder().setResult(a3.toString()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();


    }
}
