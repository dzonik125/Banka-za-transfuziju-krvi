package group8.bloodbank.model; /***********************************************************************
 * Module:  Admin.java
 * Author:  dZoNi
 * Purpose: Defines the Class Admin
 ***********************************************************************/

/**
 * @pdOid d793ee28-8894-4839-ad00-2602e252b692
 */
public class Admin extends User {

    public Admin(int id, String name, String surname, String password, Address address, String jmbg, String email, String occupation, int penalty, Gender gender) {
        super(id, name, surname, password, address, jmbg, email, occupation, penalty, gender);
    }
}