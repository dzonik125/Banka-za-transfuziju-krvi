package group8.bloodbank.mapper;

import group8.bloodbank.model.Appointment;
import group8.bloodbank.model.DTO.AppointmentCallendarDTO;

import java.util.ArrayList;
import java.util.List;

public class AppointmentCalendarMapper {

    public static List<AppointmentCallendarDTO> sourceToDestination(List<Appointment> source) {
        if ( source == null ) {
            return null;
        }
        List<AppointmentCallendarDTO> appointmentCallendarDTOs = new ArrayList<>();

        for(Appointment appointment: source) {
            AppointmentCallendarDTO dto = new AppointmentCallendarDTO();

            dto.start = appointment.getStart().toString();
            dto.title = appointment.getDonor().getName() + " " + appointment.getDonor().getSurname();
            dto.end = appointment.getStart().plusMinutes((long) appointment.getDuration()).toString();
            dto.id = appointment.getId();

            appointmentCallendarDTOs.add(dto);
        }
        return appointmentCallendarDTOs;
    }
}
