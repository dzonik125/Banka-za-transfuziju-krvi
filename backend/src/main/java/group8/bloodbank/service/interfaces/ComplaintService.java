package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.Complaint;

import java.util.List;

public interface ComplaintService {
    List<Complaint> getAll();

    Complaint answerComplaint(Long complaintID, String answer) throws Exception;

    List<Complaint> getAllUnanswered();

    void save(Complaint complaint);

    public Complaint getById(long id);
}
