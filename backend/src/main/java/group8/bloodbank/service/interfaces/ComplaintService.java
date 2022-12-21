package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.Complaint;

import java.util.List;

public interface ComplaintService {
    List<Complaint> getAll();

    Complaint answerComplaint(Complaint complaint);

    List<Complaint> getAllUnanswered();
}
