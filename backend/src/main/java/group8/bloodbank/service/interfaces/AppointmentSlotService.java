package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.AppointmentSlot;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface AppointmentSlotService {

    AppointmentSlot saveSlot(AppointmentSlot slot);

    AppointmentSlot scheduleSlot(AppointmentSlot slot) throws MessagingException, UnsupportedEncodingException;

    AppointmentSlot cancelSlot(AppointmentSlot slot);

    AppointmentSlot findById(Long id);

    List<AppointmentSlot> getAll();

    List<AppointmentSlot> getAllByBankId(Long id);
}
