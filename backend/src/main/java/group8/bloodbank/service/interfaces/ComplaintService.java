package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.Complaint;

import java.util.List;

public interface ComplaintService {
    List<Complaint> getAll();

    void answerComplaint(Long complaintID, String answer);

    List<Complaint> getAllUnanswered();
}
