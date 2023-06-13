package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.BloodUnitRequest;

import java.util.List;

public interface BloodUnitRequestService {
    public List<BloodUnitRequest> getAll();
    public void save(BloodUnitRequest bloodUnitRequest);
    public List<BloodUnitRequest> getAllWaitingForDelivery();

}
