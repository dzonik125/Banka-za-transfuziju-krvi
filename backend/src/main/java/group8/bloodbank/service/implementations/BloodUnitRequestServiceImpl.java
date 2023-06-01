package group8.bloodbank.service.implementations;

import group8.bloodbank.model.*;
import group8.bloodbank.repository.BloodUnitRequestRepository;
import group8.bloodbank.service.interfaces.BloodUnitRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class BloodUnitRequestServiceImpl implements BloodUnitRequestService {

    private final BloodUnitRequestRepository bloodUnitRequestRepository;

    @Autowired
    public BloodUnitRequestServiceImpl(BloodUnitRequestRepository bloodUnitRequestRepository) {
        this.bloodUnitRequestRepository = bloodUnitRequestRepository;
    }

    @Override
    public List<BloodUnitRequest> getAll() {
        return bloodUnitRequestRepository.findAll();
    }

    @Override
    public void save(BloodUnitRequest bloodUnitRequest) {
        bloodUnitRequestRepository.save(bloodUnitRequest);
    }

    @Override
    public List<BloodUnitRequest> getAllWaitingForDelivery() {
        List<BloodUnitRequest> requests = new ArrayList<BloodUnitRequest>();
        for(BloodUnitRequest bloodUnitRequest : getAll()) {
            if((bloodUnitRequest.getDeliveryDate().isBefore(LocalDate.now()) || bloodUnitRequest.getDeliveryDate().isEqual(LocalDate.now())) &&
                bloodUnitRequest.getStatus() == BloodUnitRequestStatus.WAITING) {
                requests.add(bloodUnitRequest);
            }
        }
        return requests;
    }
}
