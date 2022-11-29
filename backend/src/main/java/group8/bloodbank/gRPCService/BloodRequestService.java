package group8.bloodbank.gRPCService;

import blood.Blood;
import blood.BloodProviderGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

@GrpcService
class BloodRequestService extends BloodProviderGrpc.BloodProviderImplBase {
    @Override
    public void sendUrgentBloodUnits(Blood.BloodUnitRequest request, StreamObserver<Blood.BloodResponse> responseObserver) {
            String apiKey = request.getBankBankApiKey();
            List<Blood.BloodUnit> units = request.getBloodUnitsList();
            //TODO: Parsirati request i proveriti da li ima krvi u bankama
            Blood.BloodResponse res = Blood.BloodResponse.newBuilder().setStatus(Blood.RequestResponseStatus.BLOOD_AVAILABLE).build();

            responseObserver.onNext(res);
            responseObserver.onCompleted();
   }
}
