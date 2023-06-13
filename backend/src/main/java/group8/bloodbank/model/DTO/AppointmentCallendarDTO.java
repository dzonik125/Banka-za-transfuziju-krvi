package group8.bloodbank.model.DTO;

import group8.bloodbank.model.AppointmentStatus;

public class AppointmentCallendarDTO {

    public Long id;
    public String title;
    public String start;
    public String end;
    public Long donorId;
    public AppointmentStatus status;

}
