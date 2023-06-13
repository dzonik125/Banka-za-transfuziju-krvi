package group8.bloodbank.mapper;

import group8.bloodbank.model.AppointmentHistory;
import group8.bloodbank.model.DTO.AppointmentHistoryDTO;

import java.time.LocalDateTime;

public class AppointmentHistoryMapper {

    public static AppointmentHistory dtoToModel(AppointmentHistoryDTO appointmentHistoryDto) {
        AppointmentHistory appointmentHistory = new AppointmentHistory();
        appointmentHistory.setDate(LocalDateTime.now());
        appointmentHistory.setStatus(appointmentHistoryDto.getStatus());
        appointmentHistory.setBloodType(appointmentHistoryDto.getBloodType());
        appointmentHistory.setAmount(appointmentHistoryDto.getAmount());
        return appointmentHistory;
    }
}
