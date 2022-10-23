package group8.bloodbank.model; /***********************************************************************
 * Module:  Item.java
 * Author:  david
 * Purpose: Defines the Class Item
 ***********************************************************************/

/**
 * @pdOid 67762e00-bfac-4da1-b53c-d466a4467f6e
 */
public class Item {

    private String name;

    private int quantity;

    private BloodBank bloodBank;

    public Item(String name, int quantity, BloodBank bloodBank) {
        this.name = name;
        this.quantity = quantity;
        this.bloodBank = bloodBank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BloodBank getBloodBank() {
        return bloodBank;
    }

    public void setBloodBank(BloodBank bloodBank) {
        this.bloodBank = bloodBank;
    }
}