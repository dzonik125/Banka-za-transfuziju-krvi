package group8.bloodbank.controller;


import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.Complaint;
import group8.bloodbank.model.DTO.BloodBankDTO;
import group8.bloodbank.model.DTO.ChangePasswordDTO;
import group8.bloodbank.service.interfaces.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
@CrossOrigin(origins = "http://localhost:4200")
public class ComplaintController {

    private ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/findAllComplaints")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    public List<Complaint> getAll() {
        return complaintService.getAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/findAllUnanswered")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Complaint> getAllUnanswered() {
        return complaintService.getAllUnanswered();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/answerComplaint")
    public ResponseEntity<Boolean> answerComplaint(@RequestBody ChangePasswordDTO dto) throws Exception {
        try {
            complaintService.answerComplaint(dto.adminID, dto.password);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
        }
    }
}
