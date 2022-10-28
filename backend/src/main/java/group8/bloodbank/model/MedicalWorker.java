package group8.bloodbank.model; /***********************************************************************
 * Module:  MedicalWorker.java
 * Author:  david
 * Purpose: Defines the Class MedicalWorker
 ***********************************************************************/

/**
 * @pdOid f711f8ab-b463-4dc5-8215-8f96fce1bf44
 */
public class MedicalWorker extends User {
    /**  @pdOid 5cbd8b79-234f-4cad-b0a1-0a8130d141ec */
    /**
     * @pdRoleInfo migr=no name=BloodBank assc=association1 mult=1..1 side=A
     */
    public BloodBank bloodBank;

    public MedicalWorker(int id, String name, String surname, String password, Address adress, String jmbg, String email, String occupation, int penalty, Gender gender) {
        super(id, name, surname, password, adress, jmbg, email, occupation, penalty, gender);

    }

    public BloodBank getBloodBank() {
        return bloodBank;
    }

    public void setBloodBank(BloodBank newBloodBank) {
        this.bloodBank = newBloodBank;
    }

}