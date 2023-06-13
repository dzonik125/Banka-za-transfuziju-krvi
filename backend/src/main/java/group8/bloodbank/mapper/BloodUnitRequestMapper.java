package group8.bloodbank.mapper;

import group8.bloodbank.model.BloodUnitRequest;
import group8.bloodbank.model.BloodUnitRequestStatus;
import group8.bloodbank.rabbitmq.messages.BloodUnitRequestMessageDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BloodUnitRequestMapper {
    public static BloodUnitRequest toModel(BloodUnitRequestMessageDTO bloodUnitRequestMessageDTO) {
        BloodUnitRequest request = new BloodUnitRequest();
        request.setStatus(BloodUnitRequestStatus.WAITING);
        request.setType(BloodUnitUrgentRequestMapper.getTypeFromString(bloodUnitRequestMessageDTO.getType()));
        request.setAmountL(bloodUnitRequestMessageDTO.getAmountL());
        request.setHospitalRequestId(bloodUnitRequestMessageDTO.getHospitalRequestId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(bloodUnitRequestMessageDTO.getDeliveryDate(), formatter);
        request.setDeliveryDate(localDate);
        return request;
    }
}
