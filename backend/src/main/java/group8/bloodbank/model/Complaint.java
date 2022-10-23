package group8.bloodbank.model; /***********************************************************************
 * Module:  Complaint.java
 * Author:  david
 * Purpose: Defines the Class Complaint
 ***********************************************************************/

/**
 * @pdOid 77577f6a-eda9-40e7-98cf-fb077b59948f
 */
public class Complaint {

    private String description;
    private int id;

    public Complaint(String description, int id) {
        this.description = description;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}