package group8.bloodbank.user;

import blood.Blood;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import blood.BloodProviderGrpc;

@GrpcService
class BloodService extends BloodProviderGrpc.BloodProviderImplBase {
    @Override
    public void getBlood(Blood.BloodRequest request, StreamObserver<Blood.BloodResponse> responseObserver) {
            System.out.println(request.getBank());
            Blood.BloodResponse res = Blood.BloodResponse.newBuilder().setCount("12").build();

            responseObserver.onNext(res);
            responseObserver.onCompleted();
    }
}
