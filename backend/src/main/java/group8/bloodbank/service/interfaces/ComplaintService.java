package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.Complaint;

import java.util.List;

public interface ComplaintService {
    List<Complaint> getAll();

    Complaint answerComplaint(Long complaintID, String answer) throws Exception;
    void answerC();

    List<Complaint> getAllUnanswered();
}
