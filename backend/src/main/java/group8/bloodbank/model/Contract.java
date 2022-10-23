package group8.bloodbank.model; /***********************************************************************
 * Module:  Contract.java
 * Author:  david
 * Purpose: Defines the Class Contract
 ***********************************************************************/

import java.time.LocalDateTime;

public class Contract {

    public BloodBank bloodBank;
    private BloodType bloodType;
    private int quantity;
    private LocalDateTime deliveryDate;

    public Contract(BloodType bloodType, int quantity, LocalDateTime deliveryDate, BloodBank bloodBank) {
        this.bloodType = bloodType;
        this.quantity = quantity;
        this.deliveryDate = deliveryDate;
        this.bloodBank = bloodBank;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public BloodBank getBloodBank() {
        return bloodBank;
    }

    public void setBloodBank(BloodBank bloodBank) {
        this.bloodBank = bloodBank;
    }
}