package group8.bloodbank.model;

import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@AllArgsConstructor
public class Admin extends User {

    @Column
    boolean firstLogin;

    public Admin(Long id, String name, String surname, String password, Address address, String jmbg, String email, String occupation, Gender gender) {
        super(id, name, surname, password, address, jmbg, email, occupation, gender, UserType.SYSTEM_ADMINISTRATOR);
    }

//    public Admin(String name, String surname, String email, String password, String jmbg, Address address, String occupation,  Gender gender) {
//        super(name, surname, email, password, jmbg, address, occupation, gender, UserType.MEDICAL_WORKER);
//
//    }

    public Admin(boolean firstLogin, String name, String surname, String email, String password, String jmbg, Address address, String occupation,  Gender gender) {
        super(name, surname, email, password, jmbg, address, occupation, gender, UserType.SYSTEM_ADMINISTRATOR);
        this.firstLogin = firstLogin;
    }

    public Admin() {

    }

    public boolean getFirstLogin() {
        return this.firstLogin;
    }
    public void setFirstLogin(boolean firstLogin) {this.firstLogin = firstLogin;}

}