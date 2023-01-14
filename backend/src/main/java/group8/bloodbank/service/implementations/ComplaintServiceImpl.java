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
    public Complaint answerComplaint(Long complaintID, String answer) {
        Complaint complaintToUpdate = complaintRepository.findById(complaintID).get();
        if(complaintToUpdate.getVersion() == 1)
            return null;

        complaintToUpdate.setAnswer(answer);
        complaintRepository.save(complaintToUpdate);
        return complaintToUpdate;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void answerC() {
        int i = 0;
    }

    public List<Complaint> getAllUnanswered() {
        return this.complaintRepository.getAllUnanswered();
    }

}
