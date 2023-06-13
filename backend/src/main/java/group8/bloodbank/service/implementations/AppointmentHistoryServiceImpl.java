package group8.bloodbank.service.implementations;

import group8.bloodbank.mapper.AppointmentHistoryMapper;
import group8.bloodbank.model.AppointmentHistory;
import group8.bloodbank.model.DTO.AppointmentHistoryDTO;
import group8.bloodbank.model.DTO.DonorDTO;
import group8.bloodbank.model.MedicalWorker;
import group8.bloodbank.repository.AppointmentHistoryRepository;
import group8.bloodbank.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class AppointmentHistoryServiceImpl implements AppointmentHistoryService {

    private final AppointmentHistoryRepository appointmentHistoryRepository;
    private final DonorService donorService;
    private final BloodBankService bloodBankService;
    private final MedicalWorkerService medicalWorkerService;
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentHistoryServiceImpl(AppointmentHistoryRepository appointmentHistoryRepository,
                                         BloodBankService bloodBankService,
                                         DonorService donorService,
                                         MedicalWorkerService medicalWorkerService,
                                         AppointmentService appointmentService) {
        this.appointmentHistoryRepository = appointmentHistoryRepository;
        this.bloodBankService = bloodBankService;
        this.donorService = donorService;
        this.medicalWorkerService = medicalWorkerService;
        this.appointmentService = appointmentService;
    }

    @Override
    public List<AppointmentHistory> getAll() {
        return appointmentHistoryRepository.findAll();
    }

    @Override
    public Optional<AppointmentHistory> findById(Long id) {
        return appointmentHistoryRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public AppointmentHistory save(AppointmentHistoryDTO appointmentHistoryDto) {
        AppointmentHistory appointmentHistory = AppointmentHistoryMapper.dtoToModel(appointmentHistoryDto);
        appointmentHistory.setDonor(donorService.getById(appointmentHistoryDto.getDonorId()));
        appointmentHistory.setBloodBank(bloodBankService.getById(appointmentHistoryDto.getBloodBankId()).get());
        MedicalWorker medicalWorker = medicalWorkerService.getByIdLocked(appointmentHistoryDto.getMedicalWorkerId());
        appointmentHistory.setMedicalWorker(medicalWorker);
        bloodBankService.updateAmountOfDonatedBlood(bloodBankService.getById(appointmentHistoryDto.getBloodBankId()).get(), appointmentHistoryDto.getBloodType());
        bloodBankService.updateEquipmentStorage(bloodBankService.getById(appointmentHistoryDto.getBloodBankId()).get(), appointmentHistoryDto.getItem());
        appointmentService.finishAppointment(appointmentHistoryDto.getAppointmentId());
        return appointmentHistoryRepository.save(appointmentHistory);
    }

    @Override
    public List<DonorDTO> getAllByBloodBankId(Long bloodBankId) {
        List<AppointmentHistory> appointmentHistories = appointmentHistoryRepository.getAllByBankId(bloodBankId);
        List<DonorDTO> donors = new ArrayList<>();
        for(AppointmentHistory appH : appointmentHistories) {
            DonorDTO donor = new DonorDTO(appH.getId(), appH.getDonor().getName(), appH.getDonor().getSurname(), appH.getDate(), appH.getBloodType());
            donors.add(donor);
        }
        return  donors;
    }
}
