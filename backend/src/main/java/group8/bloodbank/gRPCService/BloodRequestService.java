package group8.bloodbank.gRPCService;
import blood.Blood.*;
import blood.BloodProviderGrpc;
import group8.bloodbank.model.BloodType;
import group8.bloodbank.service.interfaces.BloodBankService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.List;

@GrpcService
class BloodRequestService extends BloodProviderGrpc.BloodProviderImplBase {

    @Autowired
    private BloodBankService bloodBankService;

    @Override
    public void sendUrgentBloodUnits(BloodUnitRequest request, StreamObserver<BloodResponse> responseObserver) {
            String apiKey = request.getBankBankApiKey();
            List<BloodUnit> units = request.getBloodUnitsList();
            RequestResponseStatus status = null;
            if(bloodBankService.getByApiKey(apiKey) != null) {
                HashMap<BloodType, Double> bloodUnitsMap = convertBloodUnitListToHashMap(units);
                status = bloodBankService.checkIfBloodUnitsAvailable(bloodUnitsMap, apiKey) ? RequestResponseStatus.BLOOD_AVAILABLE : RequestResponseStatus.BLOOD_NOT_AVAILABLE;
            } else {
                status = RequestResponseStatus.BLOOD_BANK_NOT_FOUND;
            }
            BloodResponse res = BloodResponse.newBuilder().setStatus(status).build();;
            responseObserver.onNext(res);
            responseObserver.onCompleted();
   }

   public HashMap<BloodType, Double> convertBloodUnitListToHashMap(List<BloodUnit> bloodUnits) {
        HashMap<BloodType, Double> bloodUnitsMap = new HashMap<>();
        for(BloodUnit bu : bloodUnits) {
            BloodType type = BloodType.values()[bu.getBloodType().getNumber()];
            bloodUnitsMap.put(type, (double)bu.getQuantity());
        }
        return bloodUnitsMap;
    }

}
