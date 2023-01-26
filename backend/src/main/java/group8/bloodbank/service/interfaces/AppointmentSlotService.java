package group8.bloodbank.service.interfaces;

import com.google.zxing.WriterException;
import group8.bloodbank.model.AppointmentSlot;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AppointmentSlotService {

    AppointmentSlot saveSlot(AppointmentSlot slot);

    boolean scheduleSlot(AppointmentSlot slot) throws MessagingException, IOException, WriterException;

    AppointmentSlot cancelSlot(AppointmentSlot slot);

    AppointmentSlot findById(Long id);

    List<AppointmentSlot> getAll();

    List<AppointmentSlot> getAllByBankId(Long id);

    Optional<AppointmentSlot> getById(Long id);
}
