package com.patientmanagementsystem.billingservice.grpc;

import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {
@Override
    public void createBillingAccount(billing.BillingRequest billingRequest, StreamObserver<BillingResponse> responseObserver) {
        log.info("Creating Billing Account {}", billingRequest.toString());
        //Business Logic

    BillingResponse response=BillingResponse.newBuilder()
            .setAccountId("1234")
            .setStatus("ACTIVE")
            .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();

}


}
