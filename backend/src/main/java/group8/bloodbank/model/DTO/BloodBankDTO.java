package group8.bloodbank.model.DTO;

import group8.bloodbank.model.Address;
import group8.bloodbank.model.MedicalWorker;
import io.grpc.netty.shaded.io.netty.handler.codec.base64.Base64Decoder;

import java.util.Base64;

public class BloodBankDTO {
    public String name;
    public String description;
    public Address address;
    public String image;

    public MedicalWorker medicalWorker;

}
