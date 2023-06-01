package group8.bloodbank.service.implementations;


import group8.bloodbank.model.Complaint;
import group8.bloodbank.repository.ComplaintRepository;
import group8.bloodbank.service.interfaces.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ComplaintServiceImpl implements ComplaintService {
    private final ComplaintRepository complaintRepository;

    @Autowired
    public ComplaintServiceImpl(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;

    }

    public List<Complaint> getAll() {
        return complaintRepository.findAll();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Complaint answerComplaint(Long complaintID, String answer) throws Exception {
        Complaint complaintToUpdate = complaintRepository.findById(complaintID).get();

        complaintToUpdate.setAnswer(answer);
        complaintRepository.save(complaintToUpdate);
        return complaintToUpdate;
    }

    public List<Complaint> getAllUnanswered() {
        return this.complaintRepository.getAllUnanswered();
    }

    @Override
    public void save(Complaint complaint) {
        complaintRepository.save(complaint);
    }

    public Complaint getById(long id) {
        return complaintRepository.findById(id).get();
    }

}
