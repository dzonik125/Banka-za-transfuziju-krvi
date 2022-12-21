package group8.bloodbank.service.implementations;


import group8.bloodbank.model.Complaint;
import group8.bloodbank.repository.ComplaintRepository;
import group8.bloodbank.service.interfaces.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {
    private final ComplaintRepository complaintRepository;

    @Autowired
    public ComplaintServiceImpl(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;

    }

    @Override
    public List<Complaint> getAll() {
        return complaintRepository.findAll();
    }

}
