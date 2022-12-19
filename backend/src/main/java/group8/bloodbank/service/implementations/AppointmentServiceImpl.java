package group8.bloodbank.service.implementations;

import group8.bloodbank.model.Appointment;
import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.Donor;
import group8.bloodbank.repository.AppointmentRepository;
import group8.bloodbank.service.interfaces.AppointmentService;
import group8.bloodbank.service.interfaces.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    AppointmentRepository repository;
    BloodBankServiceImpl bloodBankService;
    DonorService donorService;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, BloodBankServiceImpl bloodBankService, DonorService donorService) {
        this.repository = appointmentRepository;
        this.bloodBankService = bloodBankService;
        this.donorService = donorService;
    }

    @Override
    public List<Appointment> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Appointment> findAllAppointmentsByBloodBankID(Long bloodBankID) {
        List<Appointment> appointmentsForBloodBankID = repository.findAllByBloodBankID(bloodBankID);

        for (Appointment appointment: appointmentsForBloodBankID) {
            appointment.setDonor(donorService.getById(appointment.donor_id));
        }

        return  appointmentsForBloodBankID;
    }
}
