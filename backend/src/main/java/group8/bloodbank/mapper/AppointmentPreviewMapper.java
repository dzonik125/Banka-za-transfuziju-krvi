package group8.bloodbank.mapper;

import group8.bloodbank.model.AppointmentSlot;
import group8.bloodbank.model.DTO.AppointmentPreviewDTO;

import java.util.Optional;

public class AppointmentPreviewMapper {

    public static AppointmentPreviewDTO sourceToDestination(AppointmentSlot source) {
        if ( source == null ) {
            return null;
        }
        AppointmentPreviewDTO apDTO = new AppointmentPreviewDTO();
        apDTO.bloodBankName = source.bloodBank.getName();
        apDTO.donorNameAndSurname = source.getDonor().getName() + " "+ source.getDonor().getSurname();
        apDTO.startTime = source.getStartTime();
        apDTO.endTime = source.getEndTime();
        return apDTO;
    }
}
